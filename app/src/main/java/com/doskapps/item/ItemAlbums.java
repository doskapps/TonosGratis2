package com.doskapps.item;

public class ItemAlbums {

	private String id;
	private String name;
	private String image;
	private String thumb;

	public ItemAlbums(String id, String name, String image, String thumb) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.thumb = thumb;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public String getThumb() {
		return thumb;
	}

}
