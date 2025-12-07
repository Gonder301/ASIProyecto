package com.mycompany.asiproyecto.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.Permission;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;

public class GoogleDriveService {
        private static final String TOKENS_DIRECTORY_PATH = "tokens";
        private static final String CREDENTIALS_FILE_PATH = "/client_secret.json";

        public static Drive getDriveService() throws Exception {
                InputStream in = GoogleDriveService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
                GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(GsonFactory.getDefaultInstance(),
                                new InputStreamReader(in));

                FileDataStoreFactory dataStoreFactory = new FileDataStoreFactory(
                                new java.io.File(TOKENS_DIRECTORY_PATH));

                GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                                GoogleNetHttpTransport.newTrustedTransport(),
                                GsonFactory.getDefaultInstance(),
                                clientSecrets,
                                Collections.singletonList(DriveScopes.DRIVE_FILE))
                                .setDataStoreFactory(dataStoreFactory)
                                .setAccessType("offline")
                                .build();

                Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
                                .authorize("user");

                return new Drive.Builder(
                                GoogleNetHttpTransport.newTrustedTransport(),
                                GsonFactory.getDefaultInstance(),
                                credential)
                                .setApplicationName("JobApp")
                                .build();
        }

        public static String uploadFile(java.io.File file, String remoteFileName, String mimeType) throws Exception {
                Drive service = getDriveService();

                com.google.api.services.drive.model.File fileMetadata = new com.google.api.services.drive.model.File();
                fileMetadata.setName(remoteFileName);
                // fileMetadata.setParents(Collections.singletonList("folderId")); // Optional:
                // if we want a specific folder

                com.google.api.client.http.FileContent mediaContent = new com.google.api.client.http.FileContent(
                                mimeType,
                                file);

                com.google.api.services.drive.model.File fileUpload = service.files().create(fileMetadata, mediaContent)
                                .setFields("id, webViewLink")
                                .execute();

                // Make it readable to anyone with the link (optional, but often needed for
                // recruitment viewing)
                Permission newPermission = new Permission();
                newPermission.setType("anyone");
                newPermission.setRole("reader");
                service.permissions().create(fileUpload.getId(), newPermission).execute();

                return fileUpload.getWebViewLink();
        }

        public static void deleteFileByName(String fileName) throws Exception {
                Drive service = getDriveService();
                String query = "name = '" + fileName + "' and trashed = false";
                com.google.api.services.drive.model.FileList result = service.files().list()
                                .setQ(query)
                                .setSpaces("drive")
                                .setFields("nextPageToken, files(id, name)")
                                .execute();

                for (com.google.api.services.drive.model.File file : result.getFiles()) {
                        // Delete the file (move to trash or permanent delete)
                        // Here we use delete() which permanently deletes. Use update() with
                        // trashed=true for bin.
                        // Requirement says "eliminate the CV stored", typically means delete.
                        service.files().delete(file.getId()).execute();
                        System.out.println("File deleted: " + file.getName() + " (" + file.getId() + ")");
                }
        }
}
