package Interfaces;

import java.io.Serializable;
import java.util.Set;

public interface IArtist extends Serializable {
	Integer getId();

	String getName();

	String getFrom();

	String getPhoto();

	Set<IDisc> getDiscs();
}
