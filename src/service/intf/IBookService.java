package service.intf;

import entity.Book;

import java.util.List;

public interface IBookService {
    /**
     * 获取所有图书信息
     * @return
     * @throws Exception
     */
    List<Book> findAll() throws Exception;

    /**
     * 根据关键字搜索图书信息
     * @return
     * @throws Exception
     */
    Book findBookByName(String keyWord) throws Exception;

    /**
     * 添加图书信息
     * @param book
     * @throws Exception
     */
    void addBook(Book book) throws Exception;

    /**
     * 根据图书编号删除图书
     * @param bno
     * @throws Exception
     */
    void deleteByBno(int bno) throws Exception;

    /**
     * 模糊查询
     * @return
     * @throws Exception
     */
    List<Book> findLike(String keyword) throws Exception;


    /**
     * 查询一本书
     * @param bno
     * @return
     * @throws Exception
     */
    Book findBook(int bno) throws Exception;

    /**
     * 更新图书信息
     * @throws Exception
     */
    void updateBook(String name,String author,String publish,String carrier,String image,int bno) throws Exception;
}


