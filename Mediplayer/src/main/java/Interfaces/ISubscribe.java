package Interfaces;

import java.util.List;

public interface ISubscribe <T> {
	Boolean create(List<T> list);
	Boolean delete();
	Boolean update();
	Boolean read();
}
