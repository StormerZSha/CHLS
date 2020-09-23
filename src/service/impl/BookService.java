package service.impl;

import dao.impl.BookDao;
import dao.intf.IBookDao;
import entity.Book;
import service.intf.IBookService;

import java.util.List;

public class BookService implements IBookService {
    IBookDao bookDao = new BookDao();
    /**
     * 获取所有图书信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Book> findAll() throws Exception {//
        List<Book> result = bookDao.findAll();
        return result;
    }

    /**
     * 根据关键字查询图书信息
     * @param keyWord
     * @return
     * @throws Exception
     */
    @Override
    public Book findBookByName(String keyWord) throws Exception {
        return null;
    }

    /**
     * 添加一本书
     * @param book
     * @throws Exception
     */
    @Override
    public void addBook(Book book) throws Exception {
        bookDao.insert(book);
    }

    /**
     * 根据编号删除图书
     * @param bno
     * @throws Exception
     */
    @Override
    public void deleteByBno(int bno) throws Exception {
        bookDao.deleteByBno(bno);
    }

    /**
     * 模糊查询
     *
     * @param keyword
     * @return
     * @throws Exception
     */
    @Override
    public List<Book> findLike(String keyword) throws Exception{
        List<Book> result = bookDao.findLike("%"+keyword+"%");
        return result;
    }


    /**
     *根据bno查询一本书
     * @param bno
     * @return
     * @throws Exception
     */
    @Override
    public Book findBook(int bno) throws Exception {
        Book result = bookDao.findBook(bno);
        return result;
    }

    /**
     * 图书信息更新
     * @throws Exception
     */
    @Override
    public void updateBook(String name,String author,String publish,String carrier,String image,int bno) throws Exception {
        bookDao.updateBook(name,author,publish,carrier,image,bno);
    }

}
