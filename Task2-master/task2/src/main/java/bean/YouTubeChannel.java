package bean;

import java.util.HashSet;

public class YouTubeChannel extends User {
	// ================================
	// ================================
	private HashSet<Video> myVideos;
	private HashSet<YouTubeChannel> followings;
	private HashSet<YouTubeChannel> followers;
	// =================================

	public YouTubeChannel() {
		this.myVideos = new HashSet<Video>();
		this.followers = new HashSet<YouTubeChannel>();
		this.followings = new HashSet<YouTubeChannel>();
	}

	// =================================add follower
	public boolean addFollower(YouTubeChannel follower) {
		return this.followers.add(follower);
	}

	// ===================================remove follower
	public boolean removeFollower(YouTubeChannel follower) {
		return this.followers.remove(follower);
	}

	// ===================================add following
	public boolean addFollowing(YouTubeChannel following) {
		return this.followings.add(following);
	}

	// ===================================remove following
	public boolean removeFollowing(YouTubeChannel following) {
		return this.followings.add(following);
	}

	// ===================================
	// add video
	public boolean addVideo(Video video) {
		return this.myVideos.add(video);

	}

	// remove video
	public boolean removeVideo(Video video) {
		return this.myVideos.remove(video);
	}

	// edit video
	public boolean editVideo(Video preVideo, Video newVideo) {
		return this.myVideos.remove(preVideo) & this.myVideos.add(newVideo);

	}

}
