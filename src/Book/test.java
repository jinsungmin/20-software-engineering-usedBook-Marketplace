package Book;

import static org.junit.Assert.*;

import org.junit.Test;

public class test {

	@Test
	public void testBook() {
	}

	@Test
	public void testGetbookTitle() {
		Book book = new Book();
		book.setbookTitle("123");
		assertEquals("123", book.getbookTitle());
	}

	@Test
	public void testSetbookTitle() {
		Book book = new Book();
		book.setbookTitle("123");
		assertEquals("123", book.getbookTitle());
	}

}
