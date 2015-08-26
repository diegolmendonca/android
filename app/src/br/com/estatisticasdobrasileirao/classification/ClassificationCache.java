package br.com.estatisticasdobrasileirao.classification;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import br.com.estatisticasdobrasileirao.entity.Classification;

import com.google.gson.Gson;

public class ClassificationCache {

public static void writeToCache(Context context, String fileName,  String jsonResult) {
		
		FileOutputStream fos;
		try {
			
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(jsonResult.toString().getBytes());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public static Classification[] readFromCache(Context context, String fileName) {
		FileInputStream fis;
		Classification[] classification = null;
		StringBuffer fileContent = new StringBuffer();
		
		try {
			fis = context.openFileInput(fileName);
			
			byte[] reader = new byte[fis.available()];
			
			while (fis.read(reader) != -1) {
				fileContent.append(new String(reader));
			}

			classification =  new Gson().fromJson(fileContent.toString(), Classification[].class);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return classification;
	}
}
