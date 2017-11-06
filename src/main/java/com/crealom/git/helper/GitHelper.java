package com.crealom.git.helper;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class GitHelper {
	public static Repository openRepository() throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		return builder
				.readEnvironment()
				.findGitDir()
				.build();
	}
	public static Repository createNewRepository() throws IOException {
        // prepare a new folder
        File localPath = File.createTempFile("TestGitRepository", "");
        if(!localPath.delete()) {
            throw new IOException("Could not delete temporary file " + localPath);
        }

        // create the directory
        Repository repository = FileRepositoryBuilder.create(new File(localPath, ".git"));
        repository.create();

        return repository;
    }
}