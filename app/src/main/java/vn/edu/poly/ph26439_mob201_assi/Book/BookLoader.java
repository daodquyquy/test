package vn.edu.poly.ph26439_mob201_assi.Book;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.ph26439_mob201_assi.Dto.Book;

public class BookLoader {
    List<Book> listBook = new ArrayList<>();
    Book book;
    String textContent;
    public List<Book> getlistBook(InputStream inputStream) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            book = new Book();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textContent = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (book != null) {
                            if (tagName.equalsIgnoreCase("item")) {
                                listBook.add(book);
                            }

                            if (tagName.equalsIgnoreCase("title")) {
                                book.setTitle(textContent);
                            }
                            if (tagName.equalsIgnoreCase("link")) {
                                book.setLink(textContent);
                            }
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listBook;
    }
}
