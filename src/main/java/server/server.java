package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import dao.bookDao;
import dao.reviewDao;
import dao.Impl.bookImpl;
import dao.Impl.reviewimpl;
import enities.Book;

public class server {
	public static void main(String[] args) {
		try (ServerSocket serverSocket = new ServerSocket(8281);) {
			System.out.println("Server is listening on port 8281");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("New client connected");
				server temp = new server();

				Thread t = new Thread(temp.new handler(socket));
				t.start();

			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public class handler implements Runnable {
		private Socket socket;
		private bookDao bookDao;
		private reviewDao reviewDao;
		private ObjectInputStream in;
		private ObjectOutputStream out;

		public handler(Socket socket) throws IOException {
			this.socket = socket;
			this.bookDao = new bookImpl();
			this.reviewDao = new reviewimpl();

		}

		@Override
		public void run() {
			try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
					ObjectInputStream in = new ObjectInputStream(socket.getInputStream());) {
				while (true) {
					String request = in.readUTF();

					switch (request) {
					case "listRatedBooks":
						String author = in.readUTF();
						int rating = in.readInt();
						List<Book> list = bookDao.listRatedBooks(author, rating);
						list.forEach(System.out::println);
//							List<Book> list = bookDao.listRatedBooks("Robert", 3);
						out.writeObject(list);
						out.flush();
						break;
					case "countBooksByAuthor":
						Map<String, Long> map = bookDao.countBooksByAuthor();
						for (String key : map.keySet()) {
							System.out.println(key + " : " + map.get(key));
						}
						out.writeObject(bookDao.countBooksByAuthor());
						out.flush();
						break;
					case "updateReviews":
						String isbn = in.readUTF();
						String readerID = in.readUTF();
						int rating1 = in.readInt();
						String comment = in.readUTF();
						out.writeBoolean(reviewDao.updateReviews(isbn, readerID, rating1, comment));
						out.flush();
						break;

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
