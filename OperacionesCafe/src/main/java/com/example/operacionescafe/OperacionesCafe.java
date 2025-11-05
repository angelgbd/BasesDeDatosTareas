import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
@autor Angel beltran (me ayude con IA en lo que me atrape pero aprendi bastante)
*/

public class OperacionesCafe {

    
    private static void printResults(String title, MongoCollection<Document> collection, Bson filter) {
        System.out.println("\n--- " + title + " ---");
       
        Bson finalFilter = (filter == null) ? new Document() : filter;
        if (collection.countDocuments(finalFilter) == 0) {
            System.out.println("No se encontraron documentos.");
        } else {
            collection.find(finalFilter).forEach((Consumer<Document>) doc -> System.out.println(doc.toJson()));
        }
    }

    public static void main(String[] args) {
        // 1. Conexión al Cliente de MongoDB
        String uri = "mongodb://localhost:27017";
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // 2. Obtener Base de Datos y Colección
            MongoDatabase database = mongoClient.getDatabase("restaurants");
            MongoCollection<Document> collection = database.getCollection("cafes");

            // Limpieza (Opcional): Borrar la colección para empezar de cero
            collection.drop();
            System.out.println("Colección 'cafes' limpiada para empezar.");

            // Insertar un solo documento
            System.out.println("\n=== 1. INSERT ONE ===");
            Document cafePlaza = new Document("name", "Café de la Plaza")
                    .append("stars", 4.3)
                    .append("categories", Arrays.asList("Café", "Postres", "Desayuno"));
            collection.insertOne(cafePlaza);
            System.out.println("Documento 'Café de la Plaza' insertado.");

            
            System.out.println("\n=== 2. INSERT MANY ===");
            Document espresso = new Document("name", "Espresso Express")
                    .append("stars", 4.8)
                    .append("categories", Arrays.asList("Café", "Rápido", "Takeaway"));

            Document teaHouse = new Document("name", "The Tea House")
                    .append("stars", 3.9)
                    .append("categories", Arrays.asList("Té", "Infusiones", "Postres"));

            Document morningBrew = new Document("name", "Morning Brew")
                    .append("stars", 4.0)
                    .append("categories", Arrays.asList("Café", "Desayuno", "Bakery"));

            List<Document> cafesList = Arrays.asList(espresso, teaHouse, morningBrew);
            collection.insertMany(cafesList);
            System.out.println("Insertados " + cafesList.size() + " documentos adicionales.");

            // Estado actual
            printResults("Estado actual de la colección", collection, null);

            // --- 3. Filtros (Find) ---
            System.out.println("\n=== 3. FILTROS (FIND) ===");

            // 3a. Documentos con stars >= 4.5
            Bson filterStarsGte45 = Filters.gte("stars", 4.5);
            printResults("Filtro: stars >= 4.5", collection, filterStarsGte45);

            
            Bson filterNameCafe = Filters.regex("name", "Café");
            printResults("Filtro: nombre contiene 'Café'", collection, filterNameCafe);

            // 3c. Documentos con categories que incluyan "Postres"
            Bson filterCatPostres = Filters.eq("categories", "Postres");
            printResults("Filtro: categories incluye 'Postres'", collection, filterCatPostres);

            // 3d. Documentos con stars entre 3 y 4.3 (inclusivo)
            Bson filterStarsBetween = Filters.and(
                    Filters.gte("stars", 3.0),
                    Filters.lte("stars", 4.3)
            );
            printResults("Filtro: stars entre 3.0 y 4.3", collection, filterStarsBetween);

            // 3e. Documentos cuyo nombre empieza con "T"
            Bson filterNameStartsWithT = Filters.regex("name", "^T");
            printResults("Filtro: nombre empieza con 'T'", collection, filterNameStartsWithT);


            // --- 4. Updates ---
            System.out.println("\n=== 4. UPDATES ===");

            // 4a. Cambiar stars a 4.5 para "Morning Brew"
            Bson filterMorningBrew = Filters.eq("name", "Morning Brew");
            Bson updateStars = Updates.set("stars", 4.5);
            UpdateResult result1 = collection.updateOne(filterMorningBrew, updateStars);
            System.out.println("Update (4a) 'Morning Brew' stars: " + result1.getModifiedCount() + " modificado.");

            // 4b. Incrementar stars +0.2 para "Bakery" o "Desayuno"
            Bson filterBakeryOrDesayuno = Filters.in("categories", "Bakery", "Desayuno");
            Bson updateIncStars = Updates.inc("stars", 0.2);
            UpdateResult result2 = collection.updateMany(filterBakeryOrDesayuno, updateIncStars);
            System.out.println("Update (4b) 'Bakery/Desayuno' inc stars: " + result2.getModifiedCount() + " modificados.");

            // 4c. Agregar phone y open a "Café de la Plaza"
            Bson filterCafePlaza = Filters.eq("name", "Café de la Plaza");
            Bson updateAddFields = Updates.combine(
                    Updates.set("phone", "555-111-2222"),
                    Updates.set("open", true)
            );
            UpdateResult result3 = collection.updateOne(filterCafePlaza, updateAddFields);
            System.out.println("Update (4c) 'Café de la Plaza' add fields: " + result3.getModifiedCount() + " modificado.");

            // Ver el estado después de los updates
            printResults("Estado después de UPDATES", collection, null);

            // --- 5. Deletes ---
            System.out.println("\n=== 5. DELETES ===");

            // 5a. Eliminar "Espresso Express"
            Bson filterEspresso = Filters.eq("name", "Espresso Express");
            DeleteResult delResult1 = collection.deleteOne(filterEspresso);
            System.out.println("Delete (5a) 'Espresso Express': " + delResult1.getDeletedCount() + " eliminado.");

            // 5b. Eliminar todos con stars < 4
            Bson filterStarsLt4 = Filters.lt("stars", 4.0);
            DeleteResult delResult2 = collection.deleteMany(filterStarsLt4);
            System.out.println("Delete (5b) stars < 4: " + delResult2.getDeletedCount() + " eliminados.");
            // Nota: "The Tea House" (3.9) debe ser eliminado aquí.

            // 5c. Eliminar con "Takeaway" o "Infusiones"
            Bson filterTakeawayOrInfusiones = Filters.in("categories", "Takeaway", "Infusiones");
            DeleteResult delResult3 = collection.deleteMany(filterTakeawayOrInfusiones);
            System.out.println("Delete (5c) 'Takeaway' o 'Infusiones': " + delResult3.getDeletedCount() + " eliminados.");
            // Nota: Los documentos que coincidían (Espresso, Tea House) ya fueron eliminados por 5a y 5b.

            // --- ESTADO FINAL ---
            printResults("Estado FINAL de la colección", collection, null);

        } catch (Exception e) {
            System.err.println("Ocurrió un error en la operación de MongoDB:");
            e.printStackTrace();
        }
    }
}
