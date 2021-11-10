package Interfaces;

import java.time.LocalDate;
import java.util.Set;

public interface IDisc {
	Integer getId();

    String getName();

    LocalDate getReleaseDate();

    String getPhoto();

    IArtist getArtist();

    Set<ISong> getSongs();
}
