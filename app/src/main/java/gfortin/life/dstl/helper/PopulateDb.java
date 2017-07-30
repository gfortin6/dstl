package gfortin.life.dstl.helper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class PopulateDb {



public static void populateItem(DatabaseHelper dbHelper) {
	/*try {
		InputStream input = dbHelper.getContext().getApplicationContext()
				.getAssets().open("sectionUuid.txt");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(input));
		String line;
        while ((line = in.readLine()) != null) {
		  StringTokenizer stTok = new StringTokenizer(line,"|");
		  
		  String sectionId = stTok.nextToken();
		  String titreSectionId = stTok.nextToken();
		  String numero = stTok.nextToken();
		  String titre = stTok.nextToken();
		  
		  Section section = new Section();
		  section.setId(sectionId);
		  section.setNumero(numero);
		  
		  TitreSection titreSection = new TitreSection(section,titre,"fr");
		  titreSection.setId(titreSectionId);
		  
		  dbHelper.getSectionDao().create(section);
		  dbHelper.getTitreSectionDao().create(titreSection);
		  
		}
		in.close();
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	}
	*/
}



}