package com.music.app.track.models.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.music.app.track.models.entity.Track;

public interface TrackRepo extends PagingAndSortingRepository<Track, Long> {

}
