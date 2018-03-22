# opengraph-generator
Open Graph Protocol implementation for Java. Based on the specifications at http://ogp.me/. **Still in development**.


## EXAMPLE OF USE
There is a quickly and easy example of use of this library in the main method of the [OpenGraphGenerator.java](https://github.com/FacundoAdorno/opengraph-generator/blob/master/src/main/java/ar/edu/unlp/sedici/opengraph_generator/OpenGraphGenerator.java#L254) main class

~~~java
public static void main(String[] args) {
  OpenGraphGenerator ogg = new OpenGraphGenerator();
  ogg.setArticleType();
  ogg.setTitle("Repositorios DSpace con múltiples contextos OAI-PMH");
  ogg.setLocale("es");
  ogg.addAlternativeLocale("en");
  ogg.addAlternativeLocale("pt");
  ogg.addAlternativeLocale("fr");
  ogg.addAlternativeLocale("jp");
  ArticleType article = (ArticleType) ogg.getType();
  article.addAuthor("https://www.researchgate.net/profile/Facundo_Adorno");
  article.addAuthor("https://www.researchgate.net/profile/Ariel_Lira");
  article.addAuthor("https://www.researchgate.net/profile/Marisa_De_Giusti");
  article.setPublishedTime("2014-11");
  article.addTag("DSpace"); article.addTag("xoai"); article.addTag("SEDICI"); article.addTag("data provider");
  for (OpenGraphMetadata ogm : ogg.getAllObjectMetadata()) {
	  System.out.println(ogm.print());
  }
}
~~~
