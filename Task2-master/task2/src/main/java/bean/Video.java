package bean;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Video {

	private int videoId;
	private LocalDate trendingDate;
	private String title;
	private String channelTitle;
	private int categoryId;
	private LocalDateTime publishTime;
	private String tags;
	private int views;
	private int likes;
	private int dislikes;
	private int commentCount;
	private String thumbnailLink;
	private boolean commentsDisabled;
	private boolean ratingsDisabled;
	private boolean videoErrorOrRemoved;
	private String description;

	public Video() { }

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public LocalDate getTrendingDate() {
		return trendingDate;
	}

	public void setTrendingDate(LocalDate trendingDate) {
		this.trendingDate = trendingDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public LocalDateTime getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(LocalDateTime publishTime) {
		this.publishTime = publishTime;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public String getThumbnailLink() {
		return thumbnailLink;
	}

	public void setThumbnailLink(String thumbnailLink) {
		this.thumbnailLink = thumbnailLink;
	}

	public boolean isCommentsDisabled() {
		return commentsDisabled;
	}

	public void setCommentsDisabled(boolean commentsDisabled) {
		this.commentsDisabled = commentsDisabled;
	}

	public boolean isRatingsDisabled() {
		return ratingsDisabled;
	}

	public void setRatingsDisabled(boolean ratingsDisabled) {
		this.ratingsDisabled = ratingsDisabled;
	}

	public boolean isVideoErrorOrRemoved() {
		return videoErrorOrRemoved;
	}

	public void setVideoErrorOrRemoved(boolean videoErrorOrRemoved) {
		this.videoErrorOrRemoved = videoErrorOrRemoved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
