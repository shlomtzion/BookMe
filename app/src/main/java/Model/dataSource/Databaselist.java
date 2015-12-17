package Model.dataSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import Model.Backend.Backend;
import entities.Book;
import entities.Book_Provider;
import entities.Client;
import entities.Client_Provider;
import entities.Invitation;
import entities.Privileging;
import entities.Provider;
import entities.TypeBook;

/**
 * Created by UserWin on 12/11/2015.
 */
public class Databaselist implements Backend {
    private ArrayList<Book>booklist = new ArrayList<Book>();
    private ArrayList<Client>clientlist = new ArrayList<Client>();
    private ArrayList<Provider>providerlist = new ArrayList<Provider>();
    private ArrayList<Client_Provider>client_providerlist = new ArrayList<Client_Provider>();
    private ArrayList<Invitation>invitationlist = new ArrayList<Invitation>();
    private ArrayList<Book_Provider>book_providerlist = new ArrayList<Book_Provider>();

    private int clientCounter=0;
    private int bookCounter=0;
    private int providerCounter=0;

    @Override
    public void addBook(Book book, Privileging privileging)throws Exception{
        if(privileging == Privileging.CLIENT)
            throw new Exception("Client can not add a book");
        if(booklist.size()!=0) {
            for (Book bookItem : booklist) {
                if (bookItem.equals(book))
                throw new Exception("The book has a list. ");
            }
        }
        book.setId_book(++bookCounter);
        booklist.add(book);

    }

    @Override
    public void addProvider(Provider provider, Privileging privileging)throws Exception{
        if(privileging == Privileging.CEO) {
            if(providerlist.size()!=0)
            {
            for (Provider providerItem : providerlist) {
                if (providerItem.equals(provider))
                throw new Exception("The provider has a list. ");
            }
            }
                provider.setId_provider(++providerCounter);
                providerlist.add(provider);

        }
        else throw new Exception("only the CEO can add provider");

    }

    @Override
    public String addClient(Client client)throws Exception {
        if(clientlist.size()!=0) {
            for (Client clientItem : clientlist) {
                if (clientItem.equals(client))
                    throw new Exception("The client has a list.");
                }
            }
        client.setIdClient(++clientCounter);
        String pass = doingPassword(client.getIdClient(),Privileging.CLIENT);
        clientlist.add(client);
        return pass;
    }

    @Override
    public void deleteBook (long idBook, long idProvider, Privileging privileging)throws Exception {
        if(privileging==Privileging.CLIENT)
            throw new Exception("Client can not delete a book");
        if(privileging==Privileging.PROVIDER){
            if(ifProviderBook(idBook,idProvider)==false)
                throw new Exception("book does not belong to provider!");
        }
        deleteConectorbyBook(idBook);
        for (Book bookItem : booklist) {
            if (bookItem.getId_book() == idBook) {
                booklist.remove(bookItem);
                return;
            }
        }
        throw new Exception("The book not found in the list. ");
    }

    /**
     * if have connection between the provider and the book.
     * @param idBook
     * @param idProvider
     * @return
     */
    private boolean ifProviderBook(long idBook, long idProvider) {
        Book_Provider bookProvider = new Book_Provider(idBook,idProvider);
        for (Book_Provider bookProviderItem: book_providerlist) {
            if (bookProviderItem.equals(bookProvider) == true)
                return true;
        }
        return false;
    }

    /**
     * delete connection between the provider and the book. (because the book deleted)
     * @param idBook
     */
    private void deleteConectorbyBook(long idBook) {
        for (Book_Provider bookconecto: book_providerlist)
        {
            if(bookconecto.getIdBook()==idBook)
            {
                book_providerlist.remove(bookconecto);
                return;
            }
        }
    }

    @Override
    public void deleteProvider(long idProvider, Privileging privileging) throws Exception {
        if(privileging != Privileging.CEO)
            throw new Exception("only the CEO can delete provider");
        deleteConectorbyProvider(idProvider);
        for(Provider providerItem:providerlist ){
            if(providerItem.getId_provider()==idProvider){
                providerlist.remove(providerItem);
                return;
            }
        }
        throw new Exception("The provider not found in the list. ");
    }

    /**
     * delete connection between the provider and the book. (because the provider deleted)
     * @param idProvider
     */
    private void deleteConectorbyProvider(long idProvider) {
        for (Book_Provider providerconecto: book_providerlist){
            if(providerconecto.getIdBook()==idProvider){
                book_providerlist.remove(providerconecto);
                break;
            }
        }
        for(Client_Provider providerconecto:client_providerlist){
            if( providerconecto.getIdProvider()==idProvider){
                client_providerlist.remove( providerconecto);
                return;
            }
        }
    }

    @Override
    public void deleteClient(long idClient, Privileging privileging) throws Exception {
        if(privileging != Privileging.CEO)
            throw new Exception("only the CEO can delete client");
        deleteConectorbyClient(idClient);
        for(Client clientItem:clientlist){
            if(clientItem.getIdClient()==idClient){
                clientlist.remove(clientItem);
                return;
            }
        }
        throw new Exception("The client not found in the list. ");
    }

    /**
     * delete connection between the provider and the client. (because the client deleted)
     * @param idClient
     */
    private void deleteConectorbyClient(long idClient) {
        for(Client_Provider clientconecto:client_providerlist){
            if(clientconecto.getIdProvider()==idClient){
                client_providerlist.remove( clientconecto);
                return;
            }
        }
    }

    @Override
    public void updateBook(Book book, long idProvider, Privileging privileging) throws Exception {
        if(privileging==Privileging.CLIENT)
            throw new Exception("Client can not update a book");
        if(privileging==Privileging.PROVIDER){
            if(ifProviderBook(book.getId_book(),idProvider)==false)
                throw new Exception("book does not belong to provider!");
        }
        for (Book bookItem : booklist) {
            if (bookItem.getId_book() == book.getId_book()) {
                bookItem.setName(book.getName());
                bookItem.setAuthor(book.getAuthor());
                bookItem.setPublisher(book.getPublisher());
                bookItem.setPublication(book.getPublication());
                bookItem.setPrice(book.getPrice());
                bookItem.setCount(book.getCount());
                bookItem.setTypeBook(book.getTypeBook());
                return;
            }
        }
        throw new Exception("The book is not found");

    }

    @Override
    public void updateProvider(Provider provider, Privileging privileging) throws Exception {
        if(privileging != Privileging.CEO)
            throw new Exception("only the CEO can update provider");
        for(Provider providerItem:providerlist ){
            if(providerItem.getId_provider()==provider.getId_provider()) {
                providerItem.setName(provider.getName());
                providerItem.setPhone(provider.getPhone());
                providerItem.setAddress(provider.getAddress());
                return;
            }
        }
        throw new Exception("The provider is not found");
    }

    @Override
    public void updateClient(Client client, Privileging privileging) throws Exception {
        if(privileging != Privileging.CEO)
            throw new Exception("only the CEO can update client");
        for(Client clientItem:clientlist) {
            if (clientItem.getIdClient() == client.getIdClient()) {
                clientItem.setName(client.getName());
                clientItem.setPhone(client.getPhone());
                clientItem.setAddress(client.getAddress());
                return;
            }
        }
        throw new Exception("The client is not found");
    }

    @Override
    public void addBookProvider(Book_Provider book_provider, Privileging privileging) throws Exception{
        if(privileging != Privileging.PROVIDER)
            throw new Exception("only the provider can add book_provider");
        if(book_providerlist.size()!=0) {
            for (Book_Provider bPItem : book_providerlist) {
                if (bPItem.equals(book_provider)!=false)
                throw new Exception("The book has a list of the provider. ");
            }
        }
        book_providerlist.add(book_provider);


    }

    @Override
    public void addClientProvider(Client_Provider client_provider) throws Exception {
        if(client_providerlist.size()!=0) {
            for (Client_Provider cPItem : client_providerlist) {
                if (cPItem.equals(client_provider))
                throw new Exception("The client has a list. ") ;
            }
        }
            client_providerlist.add(client_provider);


    }

    @Override
    public ArrayList<Book> getBookList() throws Exception {

        if(booklist.size()==0)
            throw new Exception("The books list is empty");
        return booklist;

    }

    @Override
    public ArrayList<Client>getClientList(Privileging privileging) throws Exception {
       if(clientlist.size()!=0)
            return clientlist;
        else throw new Exception("The clients list is empty");
    }



    @Override
    public ArrayList<Provider>getProviderList(Privileging privileging) throws Exception {
        if(privileging != Privileging.CEO)
           throw new Exception("Only the CEO can get a list of the providers");
        if(providerlist.size()!=0)
            return providerlist;
        else throw new Exception("The providers list is empty");
    }
    public void addInvitation(long idBook, Long idClient, int count, boolean delivery)throws Exception{
        double price=toBuy(idBook,count);
        if(delivery)
            price=price+30;//Delivery costs 30
        long idProvidr = 0;
        for (Book_Provider bookProvider: book_providerlist){
            if(bookProvider.getIdBook()==idBook){
                idProvidr=bookProvider.getIdProvider();
                break;
            }
        }
        invitationlist.add(new Invitation(idClient, idProvidr, idBook, count, price, false));
       // System.out.print(invitationlist);
        /**
         * kan hishalah SMS la client odot pirte hahazmana
         */
    }

    public double toBuy(long idBooK, int count)throws Exception{
        if(booklist.size()==0)
            throw new Exception("the book is not found");
        for(Book bookItem: booklist){
            if(bookItem.getId_book()==idBooK) {
                bookItem.setCount(bookItem.getCount() - count);
                bookItem.setCountSell(bookItem.getCount()+count);
                return (bookItem.getPrice()*count);
            }
        }
        throw new Exception("the book is not found");
    }
    public void makingStars(long idBook, int number)throws Exception{}


    public ArrayList<Book> Bestsellers ()throws Exception{
        if(booklist.size()==0)
            throw new Exception("not have a book");





        return null;
    }
    public ArrayList<Book> RecommendedBooks() throws Exception{

        ArrayList<Book>popolarBooks=booklist;
        if(popolarBooks!=null) {

            Collections.sort(popolarBooks, new Book());
            return popolarBooks;
        }
        else
            throw new Exception("רשימת הספרים ריקה");

    }


    public void findClient(long idClient,String name) throws Exception{
        for (Client clientItem : clientlist) {
            if (clientItem.getIdClient() == idClient) {
                if (clientItem.getName() == name)
                    return;
            }
        }
        throw new Exception("אחד מנתוניך שגוי, אנא נסה שוב" + name + "-----" +idClient);
    }


    public void findProvider(long idProvider,String name) throws Exception{
        for (Provider providerItem : providerlist) {
            if (providerItem.getId_provider() == idProvider) {
                if (providerItem.getName() == name)
                    return;
            }
        }
        throw new Exception("אחד מנתוניך שגוי, אנא נסה שוב");
    }

    public String doingPassword(long id, Privileging privileging){
        String password = "";
        String x = "";
        if (privileging == Privileging.CLIENT){
            x="c";

        }
        else x="p";
        password = x+id;
        String str;
        if (id<=9) {
            str = "xxx";
            password=password+str;
        }
        else {
            if (id<=99) {
                str = "xx";
                password=password+str;
            }
            else{
                if (id<=999)
                {
                    str ="x";
                    password=password+str;
                }

            }

        }

        int randomNum = (int)(Math.random()*(99-10+1)+10);
        password = password +randomNum;
        return password;
    }

    public String returnId(String password){
        String id="";
        int x=password.indexOf("x");
        id=password.substring(0,x);
        return id;

    }

    public void setBooklist()throws Exception{
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Book book = new Book("Harry Potter", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70,20, TypeBook.ADULTS);
        Book book1 = new Book("Harry Potter1", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70,15, TypeBook.ADULTS);
        Book book2 = new Book("Harry Potter2", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70,10, TypeBook.ADULTS);
        Book book3 = new Book("Harry Potter3", "rol", "asdfghj", df.parse("02/12/1993"), 45, 70,23, TypeBook.ADULTS);
        booklist.add(book);
        booklist.add(book1);
        booklist.add(book2);
        booklist.add(book3);
    }





    }
