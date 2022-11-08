package bd.emon.movies.rest

import bd.emon.movies.capture
import bd.emon.movies.common.PARAM_API_KEY
import bd.emon.movies.common.PARAM_GENRES
import bd.emon.movies.common.PARAM_INCLUDE_ADULT
import bd.emon.movies.common.PARAM_LANGUAGE
import bd.emon.movies.common.PARAM_PAGE
import bd.emon.movies.common.PARAM_RELEASE_YEAR
import bd.emon.movies.common.PARAM_SORT_BY
import bd.emon.movies.common.PARAM_VOTE_COUNT_GREATER_THAN
import bd.emon.movies.common.RELEASE_YEAR
import bd.emon.movies.common.toApiParam
import bd.emon.movies.entity.Optional
import bd.emon.movies.fakeData.MovieApiDummyDataProvider
import io.reactivex.rxjava3.core.Observable
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyMap
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRestRepositoryTest {

    val API_KEY = "API_KEY"
    val LANG = "LANGUAGE"
    val SORT_BY = "sort_by"
    val INCLUDE_ADULT = true
    val PAGE = 11
    val VOTE_COUNT_GREATER_THAN = 10000
    val GENRE = 28

    @Mock
    lateinit var movieRestApiInterface: MovieRestApiInterface
    lateinit var movieRestRepository: MovieRestRepository

    @Captor
    lateinit var mapCaptor: ArgumentCaptor<Map<String, String>>

    @Before
    fun setUp() {
        movieRestRepository = MovieRestRepositoryImpl(movieRestApiInterface)
        getGenresApiResponse()
        getDiscoverMoviesApiResponse()
    }

    @Test
    fun getGenres_correctParametersPassedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        movieRestRepository.getGenres(params)
        verify(movieRestApiInterface, times(1))
            .getGenres(
                capture(mapCaptor)
            )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
    }

    @Test
    fun getGenres_correctResponseReturned() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        movieRestRepository.getGenres(params).subscribe {
            assertThat(it, `is`(`is`(Optional.of(MovieApiDummyDataProvider.genreList))))
        }
    }

    @Test
    fun getDiscoverMovies_correctParams_apiKey_lang_genre_passedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        params[PARAM_GENRES] = GENRE
        movieRestRepository.getDiscoverMovies(params)
        verify(movieRestApiInterface, times(1)).getDiscoverMovies(
            capture(mapCaptor)
        )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
        assertThat(mapCaptor.value[PARAM_GENRES.toApiParam()], `is`("$GENRE"))
    }

    @Test
    fun getDiscoverMovies_correctParams_apiKey_lang_genre_sortBy_passedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        params[PARAM_GENRES] = GENRE
        params[PARAM_SORT_BY] = SORT_BY
        movieRestRepository.getDiscoverMovies(params)
        verify(movieRestApiInterface, times(1)).getDiscoverMovies(
            capture(mapCaptor)
        )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
        assertThat(mapCaptor.value[PARAM_GENRES.toApiParam()], `is`("$GENRE"))
        assertThat(mapCaptor.value[PARAM_SORT_BY.toApiParam()], `is`(SORT_BY))
    }

    @Test
    fun getDiscoverMovies_correctParams_apiKey_lang_genre_sortBy_includeAdult_passedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        params[PARAM_GENRES] = GENRE
        params[PARAM_SORT_BY] = SORT_BY
        params[PARAM_INCLUDE_ADULT] = INCLUDE_ADULT
        movieRestRepository.getDiscoverMovies(params)
        verify(movieRestApiInterface, times(1)).getDiscoverMovies(
            capture(mapCaptor)
        )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
        assertThat(mapCaptor.value[PARAM_GENRES.toApiParam()], `is`("$GENRE"))
        assertThat(mapCaptor.value[PARAM_SORT_BY.toApiParam()], `is`(SORT_BY))
        assertThat(
            mapCaptor.value[PARAM_INCLUDE_ADULT.toApiParam()],
            `is`(INCLUDE_ADULT.toString())
        )
    }

    @Test
    fun getDiscoverMovies_correctParams_apiKey_lang_genre_sortBy_includeAdult_page_passedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        params[PARAM_GENRES] = GENRE
        params[PARAM_SORT_BY] = SORT_BY
        params[PARAM_INCLUDE_ADULT] = INCLUDE_ADULT
        params[PARAM_PAGE] = PAGE
        movieRestRepository.getDiscoverMovies(params)
        verify(movieRestApiInterface, times(1)).getDiscoverMovies(
            capture(mapCaptor)
        )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
        assertThat(mapCaptor.value[PARAM_GENRES.toApiParam()], `is`("$GENRE"))
        assertThat(mapCaptor.value[PARAM_SORT_BY.toApiParam()], `is`(SORT_BY))
        assertThat(
            mapCaptor.value[PARAM_INCLUDE_ADULT.toApiParam()],
            `is`(INCLUDE_ADULT.toString())
        )
        assertThat(mapCaptor.value[PARAM_PAGE.toApiParam()], `is`(PAGE.toString()))
    }

    @Test
    fun getDiscoverMovies_correctParams_apiKey_lang_genre_sortBy_includeAdult_page_voteCountGreaterThan_passedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        params[PARAM_GENRES] = GENRE
        params[PARAM_SORT_BY] = SORT_BY
        params[PARAM_INCLUDE_ADULT] = INCLUDE_ADULT
        params[PARAM_PAGE] = PAGE
        params[PARAM_VOTE_COUNT_GREATER_THAN] = VOTE_COUNT_GREATER_THAN
        movieRestRepository.getDiscoverMovies(params)
        verify(movieRestApiInterface, times(1)).getDiscoverMovies(
            capture(mapCaptor)
        )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
        assertThat(mapCaptor.value[PARAM_GENRES.toApiParam()], `is`("$GENRE"))
        assertThat(mapCaptor.value[PARAM_SORT_BY.toApiParam()], `is`(SORT_BY))
        assertThat(
            mapCaptor.value[PARAM_INCLUDE_ADULT.toApiParam()],
            `is`(INCLUDE_ADULT.toString())
        )
        assertThat(mapCaptor.value[PARAM_PAGE.toApiParam()], `is`(PAGE.toString()))
        assertThat(
            mapCaptor.value[PARAM_VOTE_COUNT_GREATER_THAN.toApiParam()],
            `is`(VOTE_COUNT_GREATER_THAN.toString())
        )
    }

    @Test
    fun getDiscoverMovies_correctParams_apiKey_lang_genre_sortBy_includeAdult_page_voteCountGreaterThan_releaseYear_passedToApi() {
        val params = hashMapOf<String, Any?>()
        params[PARAM_API_KEY] = API_KEY
        params[PARAM_LANGUAGE] = LANG
        params[PARAM_GENRES] = GENRE
        params[PARAM_SORT_BY] = SORT_BY
        params[PARAM_INCLUDE_ADULT] = INCLUDE_ADULT
        params[PARAM_PAGE] = PAGE
        params[PARAM_VOTE_COUNT_GREATER_THAN] = VOTE_COUNT_GREATER_THAN
        params[PARAM_RELEASE_YEAR] = RELEASE_YEAR
        movieRestRepository.getDiscoverMovies(params)
        verify(movieRestApiInterface, times(1)).getDiscoverMovies(
            capture(mapCaptor)
        )
        assertThat(mapCaptor.value[PARAM_API_KEY.toApiParam()], `is`(API_KEY))
        assertThat(mapCaptor.value[PARAM_LANGUAGE.toApiParam()], `is`(LANG))
        assertThat(mapCaptor.value[PARAM_GENRES.toApiParam()], `is`("$GENRE"))
        assertThat(mapCaptor.value[PARAM_SORT_BY.toApiParam()], `is`(SORT_BY))
        assertThat(
            mapCaptor.value[PARAM_INCLUDE_ADULT.toApiParam()],
            `is`(INCLUDE_ADULT.toString())
        )
        assertThat(mapCaptor.value[PARAM_PAGE.toApiParam()], `is`(PAGE.toString()))
        assertThat(
            mapCaptor.value[PARAM_VOTE_COUNT_GREATER_THAN.toApiParam()],
            `is`(VOTE_COUNT_GREATER_THAN.toString())
        )
        assertThat(mapCaptor.value[PARAM_RELEASE_YEAR.toApiParam()], `is`(RELEASE_YEAR))
    }

    private fun getGenresApiResponse() {
        Mockito.`when`(movieRestApiInterface.getGenres(anyMap()))
            .thenReturn(Observable.just((MovieApiDummyDataProvider.genreList)))
    }

    private fun getDiscoverMoviesApiResponse() {
        Mockito.`when`(
            movieRestApiInterface.getDiscoverMovies(
                anyMap()
            )
        )
            .thenReturn(Observable.just(MovieApiDummyDataProvider.disocoverMovies))
    }
}