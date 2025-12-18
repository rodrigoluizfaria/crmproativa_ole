import static com.mongodb.client.model.Filters.eq;

import java.io.File;

import com.proativaservicos.service.api.MongoDB;

public class TesteQueryMongo {

	public static void main(String[] args) {

		MongoDB mongoDB =  new MongoDB("importacao","importacao_log");




	}

	private static boolean  rename(File orig, File dest) {

		return orig.renameTo(dest);
	}
}
