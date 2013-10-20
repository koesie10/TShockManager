package co.tshock.manager;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Run this class using your JRE on your development box, not on your Android
 * device!
 * 
 * Steps to run this are described at {@linkplain http
 * ://ormlite.com/javadoc/ormlite
 * -core/doc-files/ormlite_4.html#Config-Optimization}
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {
	public static void main(String[] args) throws Exception {
		writeConfigFile("ormlite_config.txt");
	}
}
