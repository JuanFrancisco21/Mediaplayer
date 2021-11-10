package Interfaces;

import java.util.Set;

public interface ISong {
    Integer getId();

    String getName();

    Integer getDuration();

    IGender getGenre();

    Set<IList> getLists();

    IDisc getDisc();

    Set<IPlay> getReproductions();
}
