package Model.Backend;

/**
 * Created by UserWin on 12/11/2015.
 */
/**
 * Created by UserWin on 10/11/2015.
 */

import java.util.ArrayList;

import entities.*;


public interface Backend {

    public void addBook(Book book, Privileging privileging)throws Exception;

    /**
     * add provider to database. (only ceo can do it).
     * @param provider
     * @param privileging
     * @throws Exception
     */
    public void addProvider(Provider provider, Privileging privileging)throws Exception;

    /**
     * add client to database. (only ceo)
     * @param client
     * @throws Exception
     */
    public String addClient(Client client)throws Exception;

    /**
     * delete book from database(ceo & provider can delete)
     * @param idBook
     * @param idProvider
     * @param privileging
     * @throws Exception
     */

    public void deleteBook(long idBook, long idProvider, Privileging privileging)throws Exception;

    /**
     * delete provider from database(only ceo can)
     * @param idProvider
     * @param privileging
     * @throws Exception
     */
    public void deleteProvider(long idProvider, Privileging privileging)throws Exception;

    /**
     * delete client from database (only ceo can)
     * @param idClient
     * @param privileging
     * @throws Exception
     */
    public void deleteClient(long idClient, Privileging privileging)throws Exception;

    public void updateBook(Book book, long idProvider, Privileging privileging)throws Exception;
    public void updateProvider(Provider provider, Privileging privileging)throws Exception;
    public void updateClient(Client client, Privileging privileging)throws Exception;

    public void addBookProvider(Book_Provider book_provider, Privileging privileging)throws Exception;
    public void addClientProvider(Client_Provider client_provider) throws Exception;

    public ArrayList<Book>getBookList()throws Exception;
    public ArrayList<Client>getClientList(Privileging privileging)throws Exception;
    public ArrayList<Provider>getProviderList(Privileging privileging)throws Exception;

    /**
     * calculation how match money cost books.
     * @param idBooK
     * @param count
     * @return
     * @throws Exception
     */
    public double toBuy(long idBooK, int count, boolean delivery)throws Exception;

    public void addInvitation(long idBook, long idClient, int count,double totalPrice, boolean delivery)throws Exception;

    /**
     * add star to book)
     * @param idBook
     * @param number
     * @throws Exception
     */
    public void makingStars(long idBook, int number)throws Exception;

    /**
     * return list of 5 bestsellers book
     * @return
     * @throws Exception
     */
    public ArrayList<Book> Bestsellers()throws Exception;

    /**
     * return list of 5 books with most stars
     * @return
     * @throws Exception
     */
    public ArrayList<Book> RecommendedBooks() throws Exception;

    public void findClient(long idClient,String name) throws Exception;

    public void findProvider(long idProvider,String name) throws Exception;

    public String doingPassword(long id, Privileging privileging);

    public String returnId(String password);

    public void setBooklist()throws Exception;


}

