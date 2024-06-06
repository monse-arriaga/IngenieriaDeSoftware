package mx.ids.playbit.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mx.ids.playbit.api.ParticipantApiService
import mx.ids.playbit.api.TournamentApiService
import mx.ids.playbit.api.UserApiService
import mx.ids.playbit.repository.ParticipantRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
* The components needed in the app
* Declare them once use them everywhere :)
 * @author Leonardo Aguilar Rodríguez
* */
@Module
@InstallIn(SingletonComponent::class) // Application-level container
object AppModule {

    private const val BASE_URL = "http://aquivalaip:8080/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserApiService(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    fun provideTournamentApiService(retrofit: Retrofit): TournamentApiService =
        retrofit.create(TournamentApiService::class.java)

    @Provides
    fun provideParticipantApiService(retrofit: Retrofit): ParticipantApiService =
        retrofit.create(ParticipantApiService::class.java)

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
}
