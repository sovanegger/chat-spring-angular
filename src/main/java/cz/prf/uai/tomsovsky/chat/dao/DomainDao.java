package cz.prf.uai.tomsovsky.chat.dao;

import java.util.List;

import cz.prf.uai.tomsovsky.chat.domain.IDomain;

public interface DomainDao<T extends IDomain> {
	public Long save(T domain);
	public void update(T domain);
	public T getById(long id);
	public void delete(T domain);
	public List<T> list(int limit);
}
