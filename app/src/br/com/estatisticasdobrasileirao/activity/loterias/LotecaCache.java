package br.com.estatisticasdobrasileirao.activity.loterias;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import br.com.estatisticasdobrasileirao.entity.Loteca;

import com.google.gson.Gson;

public class LotecaCache {

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

	public static Loteca readFromCache(Context context, String fileName) {
		FileInputStream fis;
		Loteca loteca = null;
		StringBuffer fileContent = new StringBuffer();
		
		try {
			fis = context.openFileInput(fileName);
			
			byte[] reader = new byte[fis.available()];
			
			while (fis.read(reader) != -1) {
				fileContent.append(new String(reader));
			}

			loteca =  new Gson().fromJson(fileContent.toString(), Loteca.class);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return loteca;
	}

}
