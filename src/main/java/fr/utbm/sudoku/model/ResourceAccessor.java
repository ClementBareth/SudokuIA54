package fr.utbm.sudoku.model;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceAccessor {

	public static File getDemoFile() throws URISyntaxException{
		return new File(ResourceAccessor.class.getResource("/data/sudoku.db").toURI()); //$NON-NLS-1$
	}
	
	public static URL getResFileURL(String path) {
		return ResourceAccessor.class.getResource(path);
	}

}
