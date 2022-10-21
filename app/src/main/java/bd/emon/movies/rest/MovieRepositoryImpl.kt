package bd.emon.movies.rest

import bd.emon.movies.common.PARAM_API_KEY
import bd.emon.movies.common.PARAM_GENRES
import bd.emon.movies.common.PARAM_INCLUDE_ADULT
import bd.emon.movies.common.PARAM_LANGUAGE
import bd.emon.movies.common.PARAM_PAGE
import bd.emon.movies.common.PARAM_SORT_BY
import bd.emon.movies.common.PARAM_VOTE_COUNT_GREATER_THAN
import bd.emon.movies.common.toApiParam
import bd.emon.movies.entity.genre.Genres
import bd.emon.movies.entity.Optional
import bd.emon.movies.entity.discover.DiscoverMovie
import io.reactivex.rxjava3.core.Observable

class MovieRepositoryImpl(private val movieApiInterface: MovieApiInterface) : MovieRepository {
    override fun getGenres(withParam: Map<String, Any?>): Observable<Optional<Genres>> {
        val params = HashMap<String, String>()
        params[PARAM_API_KEY.toApiParam()] = withParam[PARAM_API_KEY] as String
        params[PARAM_LANGUAGE.toApiParam()] = withParam[PARAM_LANGUAGE] as String

        return movieApiInterface.getGenres(params).map {
            Optional.of(it)
        }
    }

    override fun getDiscoverMovies(
        withParam: Map<String, Any?>
    ): Observable<Optional<DiscoverMovie>> {
        val params = HashMap<String, String>()
        params[PARAM_API_KEY.toApiParam()] = withParam[PARAM_API_KEY] as String
        params[PARAM_LANGUAGE.toApiParam()] = withParam[PARAM_LANGUAGE] as String
        params[PARAM_GENRES.toApiParam()] = (withParam[PARAM_GENRES] as Int).toString()

        withParam[PARAM_SORT_BY]?.let {
            params[PARAM_SORT_BY.toApiParam()] = it as String
        }

        withParam[PARAM_INCLUDE_ADULT]?.let {
            params[PARAM_INCLUDE_ADULT.toApiParam()] = (it as Boolean).toString()
        }

        withParam[PARAM_PAGE]?.let {
            params[PARAM_PAGE.toApiParam()] = (it as Int).toString()
        }

        withParam[PARAM_VOTE_COUNT_GREATER_THAN]?.let {
            params[PARAM_VOTE_COUNT_GREATER_THAN.toApiParam()] = (it as Int).toString()
        }

        return movieApiInterface.getDiscoverMovies(params).map {
            it.grp_genre_id = withParam[PARAM_GENRES] as Int
            Optional.of(it)
        }
    }
}
