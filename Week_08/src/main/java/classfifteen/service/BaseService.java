package classfifteen.service;

public interface BaseService<T> {

    int add(T t);

    int update(T t);

    int delete(T t);

    T selectOne(T t);
}
