package org.gradle;

import static spark.Spark.*;

public class HiWorld {
	  public static void main(String[] args) {
		  setPort(5000);
          get("/", (req, res) -> "Hi World");
      }
}
