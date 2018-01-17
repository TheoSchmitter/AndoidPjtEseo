package fr.eseo.course.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import fr.eseo.course.BuildConfig;
import fr.eseo.course.data.models.CharactersList;
import fr.eseo.course.data.models.PageCharacter;
import fr.eseo.course.data.models.PagePlanet;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * ApiService
 */
public interface ApiService {

    //https://us-central1-eseo-course.cloudfunctions.net/status?identifier=
    @GET("people")
    Call<PageCharacter> readCharactersResult( @Query("") final PageCharacter myPage);
    @GET("people/")
    Call<PageCharacter> readNextPageCharacterByIndex(@Query("page") int pageIndex);

    @GET("planets")
    Call<PagePlanet> readPlanetsResult(@Query("") final PagePlanet myPage);
    @GET("planets/")
    Call<PagePlanet> readNextPagePlanetsByIndex(@Query("page") int pageIndex);





    //Call<CharactersList> readCharactersResult(@Query("results") final Character[] results);
    //Call<LedStatus> readStatus(@Query("identifier") final String identifier);

    //@GET("https://swapi.co/api/people")


    //https://us-central1-eseo-course.cloudfunctions.net/status
    @POST("/status")
    Call<CharactersList> writeStatus(@Body final CharactersList status);

    class Builder {
        /**
         * Create a singleton only for simplicity. Should be done through a DI system instead.
         */
        private static final ApiService instance = build();

        public static ApiService getInstance() {
            return instance;
        }

        private Builder() {
        }

        /**
         * Build an ApiService instance
         */
        private static ApiService build() {
            final Gson gson = new GsonBuilder().create(); // JSON deserializer/serializer

            // Create the OkHttp Instance
            final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(new HttpLoggingInterceptor()
                            .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                                    : HttpLoggingInterceptor.Level.NONE))
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(final Chain chain) throws IOException {
                            final Request request = chain.request().newBuilder()
                                    .addHeader("Accept", "application/json").build();
                            return chain.proceed(request);
                        }
                    })
                    .build();

            return new Retrofit.Builder()
                    .baseUrl("https://swapi.co/api/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(ApiService.class);
        }
    }
}
