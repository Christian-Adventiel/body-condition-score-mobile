package fr.adventiel.bcs.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import fr.adventiel.bcs.data.AppDatabase
import fr.adventiel.bcs.data.Cow
import fr.adventiel.bcs.data.Livestock
import fr.adventiel.bcs.utilities.INIT_FILE_LIVESTOCKS
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(INIT_FILE_LIVESTOCKS).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val livestockType = object : TypeToken<List<JsonLivestock>>() {}.type
                    val livestocksList: List<JsonLivestock> = Gson().fromJson(jsonReader, livestockType)

                    val database = AppDatabase.getInstance(applicationContext)
                    livestocksList.forEach { jsonLivestock ->
                        val insertedLivestockId = database.livestockDao().insert(jsonLivestock.toLivestock())

                        jsonLivestock.cows.forEach{jsonCow ->
                            jsonCow.livestockId = insertedLivestockId
                            database.cowDao().insert(jsonCow.toCow())
                        }
                    }

                    Result.success()
                }
            }


        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = SeedDatabaseWorker::class.java.simpleName
    }

    // Classes used to read the json init file.
    class JsonLivestock(
        val name: String,
        val number: String,
        val cows: List<JsonCow>
    ) {
        fun toLivestock() = Livestock(0, name, number)
    }

    class JsonCow(
        var livestockId: Long,
        val name: String,
        val workNumber: String,
        val nationalNumber: String
    ) {
        fun toCow() = Cow(0, livestockId, name, workNumber, nationalNumber)
    }
}