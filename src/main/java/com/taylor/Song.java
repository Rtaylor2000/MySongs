/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.taylor;

/**
 *
 * @author Ryan
 */
public class Song {
    int songId;
    String name;
    String artist;
    String youtube;

    public Song(int songId, String name, String artist, String youtube) {
        this.songId = songId;
        this.name = name;
        this.artist = artist;
        this.youtube = youtube;
    }
    
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }
    
}
