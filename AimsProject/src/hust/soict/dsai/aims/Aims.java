package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.store.*;
import hust.soict.dsai.aims.media.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Aims {
	public static void showMenu() {
		System.out.println("AIMS: ");
		System.out.println("--------------------------------");
		System.out.println("1. View store"); 
		System.out.println("2. Update store"); 
		System.out.println("3. See current cart"); 
		System.out.println("0. Exit"); 
		System.out.println("--------------------------------"); 
		System.out.println("Please choose a number: 0-1-2-3");
	}
	
	public static void storeMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------"); 
		System.out.println("1. See a media’s details"); 
		System.out.println("2. Add a media to cart"); 
		System.out.println("3. Play a media"); 
		System.out.println("4. See current cart"); 
		System.out.println("0. Back"); 
		System.out.println("--------------------------------"); 
		System.out.println("Please choose a number: 0-1-2-3-4");
	}
	
	public static void mediaDetailsMenu() {
		System.out.println("Options: "); 
		System.out.println("--------------------------------"); 
		System.out.println("1. Add to cart"); 
		System.out.println("2. Play");
		System.out.println("0. Back"); 
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}
	
	public static void bookDetailsMenu() {
		System.out.println("Options: "); 
		System.out.println("--------------------------------"); 
		System.out.println("1. Add to cart"); 
		System.out.println("0. Back"); 
		System.out.println("--------------------------------");
		System.out.println("Please choose a number: 0-1-2");
	}
	
	public static void cartMenu() {
		System.out.println("Options: ");
		System.out.println("--------------------------------"); 
		System.out.println("1. Filter medias in cart"); 
		System.out.println("2. Sort medias in cart"); 
		System.out.println("3. Remove media from cart"); 
		System.out.println("4. Play a media"); 
		System.out.println("5. Place order"); 
		System.out.println("0. Back"); 
		System.out.println("--------------------------------"); 
		System.out.println("Please choose a number: 0-1-2-3-4-5");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		Cart anOrder = new Cart();
		Store store = new Store();
		
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
				"Animation", "Roger Allers", 87, 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
				"Science Fiction", "George Lucas", 87, 24.95f);
		DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
				"Animation", "Roger Allerts", 90,  18.99f);
		Book b1 = new Book("Aladin", "tale", 24.95f);
		CompactDisc cd1 = new CompactDisc("Star Wars", "theme song", 30f, "George Lucas", "George Lucas");
		Track t1 = new Track("theme 1", 10);
		cd1.addTrack(t1);
		
		//here I add DVDs and test add method
 		store.addMedia(dvd1);
		store.addMedia(dvd2);
		store.addMedia(dvd3);
		store.addMedia(b1);
		store.addMedia(cd1);
		
		while (true) {
			showMenu();
			int option = scanner.nextInt();
			switch (option) {
				case 1:	
					store.printStore();
					while (true) {
						storeMenu();
						int option1 = scanner.nextInt();
						switch (option1) {
							case 1:
								scanner.nextLine();
								System.out.println("Please enter Media title: ");
								String inp = scanner.nextLine();
								List<Media> r = store.search(inp); //result of search
								if (r.size() != 0) {
									for (Media res: r) {
										System.out.println("result: " + res);
										if (res instanceof Disc) {
											while (true) {
												mediaDetailsMenu();
												int inp1 = scanner.nextInt();
												if (inp1 == 1) {
													anOrder.addMedia(res);
												}
												if (inp1 == 2) {
													try {
														((Disc) res).play();
													} catch (PlayerException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
												}
												if (inp1 == 0)
													break;
											}
											
										} else if (res instanceof Book) {
											while (true) {
												bookDetailsMenu();
												int inp1 = scanner.nextInt();
												if (inp1 == 0) {
													break;
												} 
												if (inp1 == 1) {
													anOrder.addMedia(res);
												}
											}
										}
									}
								} else 
									System.out.println("No result!");
								break;
							case 2:
								scanner.nextLine();
								System.out.println("Please enter Media title: ");
								inp = scanner.nextLine();
								r = store.search(inp); //result of search
								if (r.size() != 0) {
									for (Media m : r) {
										System.out.println("result: " + m);
										System.out.println("Option: 1.Add 0.Not add ");
										while (true) {
											int num = scanner.nextInt();
											if (num == 1) {
												anOrder.addMedia(m);
												System.out.println("added: " + m);
												break;
											} 
											if (num == 0)
												break;
										}
									}
								} else 
									System.out.println("No result!");
								break;
							case 3:
								scanner.nextLine();
								System.out.println("Please enter Media title: ");
								inp = scanner.nextLine();
								r = store.search(inp); //result of search
								if (r.size() != 0) {
									for (Media res: r) {
										System.out.println("playing: " + res);
										if (res instanceof Disc) {				
											try {
												((Disc) res).play();
											} catch (PlayerException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}	
										} else if (res instanceof Book)
											System.out.println("Cannot play book");									
									}
								} else 
									System.out.println("No result!");
								break;
							case 4:
								anOrder.print();
								break;
						}
						if (option1 == 0) 
							break;				
					}
					break;
				case 2: // allow the user to add a media to or remove a media from the store
				{
					scanner.nextLine(); 
					System.out.println("Update(u) or Remove(r) item in Store? Please choose u-r!");
					String select = scanner.nextLine();
					
					if(select.compareTo("r")==0) {
						System.out.println("Enter title of media ");
						String title = scanner.nextLine();
		
						ArrayList<Media> res = store.search(title); //result of search
						if (res.size() != 0) {
							for (Media m : res) {
								System.out.println("result: " + m);
								System.out.println("Option: 1.Delete 0.Not delete ");
								while (true) {
									int num = scanner.nextInt();
									if (num == 1) {
										store.removeMedia(m);
										break;
									} 
									if (num == 0)
										break;
								}
							}
							
						} else 
							System.out.println("No result!");
						
					}
					else if(select.compareTo("u")==0) {
						System.out.println("Title: ");
						String title = scanner.nextLine();
						System.out.println("Category: ");
						String category = scanner.nextLine();
						System.out.println("Cost: ");
						float cost = scanner.nextFloat();
						scanner.nextLine();
						System.out.println("Book or CD or DVD?");
						System.out.println("Choose book-cd-dvd!");
						String secl = scanner.nextLine();
						if(secl.compareTo("book")==0) {
							Book x = new Book(title, category, cost);
							// add author
							System.out.println("Enter list of authors. Enter \"stop\" for stop!");
							String author;
							
							do {
								author = scanner.nextLine();
								if(author.compareTo("stop")==0) continue;
								x.addAuthor(author);
							}while(author.compareTo("stop")!=0);
							store.addMedia(x);
						}
						else if(secl.compareTo("cd")==0) {
							System.out.println("Name of artist?");
							String artist = scanner.nextLine();
							System.out.println("Director?");
							String director = scanner.nextLine();
							System.out.println("Length?");
							int length = scanner.nextInt();
							CompactDisc x = new CompactDisc(title, category,cost, director, artist);
							// add track
							System.out.println("Enter information of track that is you want to add! Enter \"stop\" for stop! Enter anything(ex. more) for add more!");
							String choose;
							
							do {
								scanner.nextLine();
								choose = scanner.nextLine();
								if(choose.compareTo("stop")==0) continue;
								
								System.out.println("Information of track.\n Title: ");
								title = scanner.nextLine();
								System.out.println("Length of track: ");
								length = scanner.nextInt();
								Track intrack = new Track(title, length);
								x.addTrack(intrack);
							}while(choose.compareTo("stop")!=0);
							
							store.addMedia(x);
							
						}
						else if(secl.compareTo("dvd")==0) {
							System.out.println("Director?");
							String director = scanner.nextLine();
							System.out.println("Length?");
							int length = scanner.nextInt();
							DigitalVideoDisc x = new DigitalVideoDisc(title, category,director, length, cost);
							store.addMedia(x);
							
						}
					}
					break;
					
				}
				case 3:
					anOrder.print();
					while (true) {	
						cartMenu();
						int option1 = scanner.nextInt();
						if (option1 == 0)
							break;
						switch (option1) {
							case 1:
								scanner.nextLine();
								System.out.println("press [Y] to filter by id, [rest] is by title");
								String inp = scanner.nextLine();
								if (inp.equals("Y")) {
									int inp_id = scanner.nextInt(); 
									//for(Media m: anOrder.search(inp_id))
										//System.out.println(m);
									
								} else {
									String inp_str = scanner.nextLine();
									//for(Media m: anOrder.search(inp_str))
										//System.out.println(m);
								}
								break;
							case 2:
								scanner.nextLine();
								System.out.println("press [Y] to sort by title-cost, [rest] is by cost-title");
								inp = scanner.nextLine();
								if (inp.equals("Y")) {
									anOrder.sortByTitleCost();
								} else {
									anOrder.sortByCostTitle();
								}
								anOrder.print();
								break;
							/*case 3:
								scanner.nextLine();
								System.out.println("Please enter Media title: ");
								inp = scanner.nextLine();
								ArrayList<Media> res = anOrder.search(inp); //result of search
								if (res.size() != 0) {
									for (Media m : res) {
										System.out.println("result: " + m);
										System.out.println("Option: 1.Delete 0.Not delete ");
										while (true) {
											int num = scanner.nextInt();
											if (num == 1) {
												anOrder.removeMedia(m);
												break;
											} 
											if (num == 0)
												break;
										}
									}
									
								} else 
									System.out.println("No result!");
								break; 
							case 4:
								scanner.nextLine();
								System.out.println("Please enter Media title: ");
								inp = scanner.nextLine();
								res = anOrder.search(inp); //result of search
								if (res.size() != 0) {
									for (Media m : res) {
										if (m instanceof Disc) {				
											((Disc) m).play();	
										} else if (m instanceof Book)
											System.out.println("Cannot play book");	
									}
								} else 
									System.out.println("No result!");
								break;
							case 5:
								System.out.println("Placed Order successfully");
								anOrder = new Cart();
								break; */
						} 
					}
					break; 
			} 
			if (option == 0) 
				break;
		}
		System.out.println("See you again!");
		scanner.close();
	}
}
