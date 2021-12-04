package com.webapi.movieapp.service;

import com.webapi.movieapp.base.CrudJpaService;
import com.webapi.movieapp.dtos.*;
import com.webapi.movieapp.models.*;
import com.webapi.movieapp.repositories.ContentRepository;
import com.webapi.movieapp.security.models.AuthUserDetails;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService extends CrudJpaService<Content, Integer> {
    private final ContentRepository contentRepository;
    private final GenreService genreService;
    private final ContentGenreService contentGenreService;
    private final MoviePeopleService moviePeopleService;
    private final MovieRoleService movieRoleService;
    private final MovieCastService movieCastService;
    private final SeasonService seasonService;
    private final SeriesCastService seriesCastService;
    private final MoviePeopleRoleService moviePeopleRoleService;
    private final EpisodeService episodeService;
    private final ReviewService reviewService;
    private final ContentTypeService contentTypeService;
    private final ContentCommentService contentCommentService;
    private final ModelMapper modelMapper;

    public ContentService(ContentRepository contentRepository, ModelMapper modelMapper, GenreService genreService,
                          ContentGenreService contentGenreService, MoviePeopleService moviePeopleService,
                          MovieRoleService movieRoleService, MovieCastService movieCastService,
                          SeasonService seasonService, SeriesCastService seriesCastService,
                          MoviePeopleRoleService moviePeopleRoleService, EpisodeService episodeService,
                          ReviewService reviewService, ContentTypeService contentTypeService,
                          ContentCommentService contentCommentService) {
        super(contentRepository, modelMapper, Content.class);
        this.contentRepository = contentRepository;
        this.genreService = genreService;
        this.contentGenreService = contentGenreService;
        this.modelMapper = modelMapper;
        this.moviePeopleService = moviePeopleService;
        this.movieRoleService = movieRoleService;
        this.movieCastService = movieCastService;
        this.seasonService = seasonService;
        this.seriesCastService = seriesCastService;
        this.moviePeopleRoleService = moviePeopleRoleService;
        this.episodeService = episodeService;
        this.reviewService = reviewService;
        this.contentTypeService = contentTypeService;
        this.contentCommentService = contentCommentService;
    }

    public Content insert(ContentRequestDTO request) {
        Content content = super.insert(request, Content.class);
        insertContentGenres(request.getGenreIds(), content);
        return content;
    }

    public List<ContentBaseDTO> getAllFavouriteByUser(int userId) {
        return reviewService.findAll(Review.class).stream()
                .filter(o -> o.getUser().getUserId() == userId && o.getFavourite())
                .map(o -> modelMapper.map(o.getContent(), ContentBaseDTO.class))
                .collect(Collectors.toList());
    }

    public Content update(ContentRequestDTO request) throws NotFoundException {
        Content content = super.update(request.getContentId(), request, Content.class);
        contentGenreService.deleteByContentId(content.getContentId());
        insertContentGenres(request.getGenreIds(), content);
        return null;
    }

    private void insertContentGenres(List<Integer> genreIds, Content content) {
        genreIds.forEach(id -> {
            ContentGenreId contentGenreId = new ContentGenreId(content.getContentId(), id);
            Genre genre = null;
            try {
                genre = genreService.findById(id, Genre.class);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
            contentGenreService.insert(new ContentGenre(contentGenreId, content, genre), ContentRequestDTO.class);
        });
    }

    public List<ContentBaseDTO> getByGenreId(String contentTypeName, Integer genreId, Integer num) throws NotFoundException {
        Genre genre = genreService.findById(genreId, Genre.class);
        List<ContentBaseDTO> contents = genre.getContentGenreList()
                .stream()
                .filter(o -> o.getContent().getContentType().getName().equals(contentTypeName))
                .map(o -> modelMapper.map(o.getContent(), ContentBaseDTO.class))
                .filter(o -> o.isActive())
                .collect(Collectors.toList());
        if (num != null) {
            contents = contents.stream()
                    .limit(num)
                    .collect(Collectors.toList());
        }
        return contents;
    }

    public List<ContentBaseDTO> getAllByGenreId(Integer genreId, Integer num) throws NotFoundException {
        Genre genre = genreService.findById(genreId, Genre.class);
        List<ContentBaseDTO> contents = genre.getContentGenreList()
                .stream()
                .map(o -> modelMapper.map(o.getContent(), ContentBaseDTO.class))
                .filter(o -> o.isActive())
                .collect(Collectors.toList());
        if (num != null) {
            contents = contents.stream()
                    .limit(num)
                    .collect(Collectors.toList());
        }
        return contents;
    }


    public void delete(Integer contentId) throws NotFoundException {
        Content content = super.findById(contentId, Content.class);
        content.setActive(false);
        contentRepository.save(content);
    }

    public SingleContentDTO getSingleContent(Integer contentId) throws NotFoundException {
        SingleContentDTO singleContentDTO = super.findById(contentId, SingleContentDTO.class);
        List<GenreDTO> genreList = contentGenreService.getAllByContentId(contentId)
                .stream()
                .map(o -> modelMapper.map(o.getGenre(), GenreDTO.class))
                .collect(Collectors.toList());
        List<ContentCommentDTO> contentCommentList = contentCommentService.findAllByContentId(contentId);
        singleContentDTO.setGenres(genreList);
        singleContentDTO.setContentComments(contentCommentList);
        singleContentDTO.setFavourite(reviewService
                .getFavouriteByUserIdAndContentId(((AuthUserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal()).getUserId(),
                contentId));
        return singleContentDTO;
    }

    public boolean existsById(Integer contentId) {
        return contentRepository.existsById(contentId);
    }

    public ContentBaseDTO saveMovie(MovieInsertRequestDTO request) {
        Content content = insert(request);

        request.getMovieCastList().forEach(movieCast -> {
            MovieCastId key = new MovieCastId(content.getContentId(),
                    new MoviePeopleRoleId(movieCast.getMovieRoleId(), movieCast.getMoviePeopleId()));
            try {
                MoviePeople moviePeople = moviePeopleService.findEntityById(movieCast.getMoviePeopleId());
                MovieRole movieRole = movieRoleService.findEntityById(movieCast.getMovieRoleId());
                movieCastService.insertEntity(key, content, moviePeople, movieRole);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        });

        return modelMapper.map(content, ContentBaseDTO.class);
    }

    public ContentBaseDTO saveSeries(SeriesInsertRequestDTO request) {
        Content content = insert(request);

        request.getSeasonList().forEach(seasonDto -> {
            seasonDto.setContentId(content.getContentId());
            Season season = seasonService.insert(seasonDto, Season.class);
            seasonDto.getSeriesCastList().forEach(seriesCastDto -> {
                try {
                    SeriesCastId key = new SeriesCastId(season.getId(),
                            new MoviePeopleRoleId(seriesCastDto.getMovieRoleId(), seriesCastDto.getMoviePeopleId()));
                    MoviePeopleRole moviePeopleRole = moviePeopleRoleService
                            .findEntityById(new MoviePeopleRoleId(seriesCastDto.getMovieRoleId(), seriesCastDto.getMoviePeopleId()));
                    seriesCastService.insertEntity(new SeriesCast(key, season, moviePeopleRole));
                } catch (NotFoundException e) {
                }
            });

            seasonDto.getEpisodeList().forEach(episodeDto -> {
                episodeDto.setSeasonId(season.getSeasonId());
                episodeService.insert(episodeDto, Episode.class);
            });
        });

        return modelMapper.map(content, ContentBaseDTO.class);
    }

    public List<ContentBaseDTO> getAllByContentTypeName(String contentTypeName) {
        return contentRepository.findAll()
                .stream()
                .filter(o -> o.getContentType().getName().equals(contentTypeName))
                .map(o -> modelMapper.map(o, ContentBaseDTO.class))
                .collect(Collectors.toList());
    }

    public List<ContentBaseDTO> getAllByRating(String contentTypeName) {
        ContentType contentType = contentTypeService.getContentTypeByName(contentTypeName);
        return contentRepository.findAllByContentTypeOrderByRatingDesc(contentType)
                .stream()
                .map(o -> modelMapper.map(o, ContentBaseDTO.class))
                .filter(o -> o.isActive())
                .collect(Collectors.toList());
    }

    public List<ContentBaseDTO> getAllByReleaseDate(String contentTypeName) {
        ContentType contentType = contentTypeService.getContentTypeByName(contentTypeName);
        return contentRepository.findAllByContentTypeOrderByReleaseDateDesc(contentType)
                .stream()
                .map(o -> modelMapper.map(o, ContentBaseDTO.class))
                .filter(o -> o.isActive())
                .collect(Collectors.toList());
    }

}
