// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.store.basic;

import codeu.model.data.Activity;
import codeu.model.data.Vertex;
import codeu.model.store.persistence.PersistentStorageAgent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Store class that uses in-memory data structures to hold values and automatically loads from and
 * saves to PersistentStorageAgent. It's a singleton so all servlet classes can access the same
 * instance.
 */
public class VertexStore {

   /** Singleton instance of MessageStore. */
   private static VertexStore instance;
   
   /**
    * Returns the singleton instance of MessageStore that should be shared between all servlet
    * classes. Do not call this function from a test; use getTestInstance() instead.
    */
   public static VertexStore getInstance() {
     if (instance == null) {
       instance = new VertexStore(PersistentStorageAgent.getInstance());
     }
     return instance;
   }
   
   /**
    * Instance getter function used for testing. Supply a mock for PersistentStorageAgent.
    *
    * @param persistentStorageAgent a mock used for testing
    */
   public static VertexStore getTestInstance(PersistentStorageAgent persistentStorageAgent) {
     return new VertexStore(persistentStorageAgent);
   }

   /**
    * The PersistentStorageAgent responsible for loading Activities from and saving Activities to
    * Datastore.
    */
   private PersistentStorageAgent persistentStorageAgent;

   /** The in-memory list of Activities. */
   private List<String> data = new ArrayList<>();
   
   /*
    * Method to get the list of activities
    */
   public List<String> getAllVerticies() {
 	  return Collections.unmodifiableList(data);
   }

   /** This class is a singleton, so its constructor is private. Call getInstance() instead. */
   private VertexStore(PersistentStorageAgent persistentStorageAgent) {
     this.persistentStorageAgent = persistentStorageAgent;
     data = new ArrayList<>();
   }

   /** Add a new activity to the current set of activities known to the application. */
   public void addVertex(String v) {
	   data.add(v);
     persistentStorageAgent.writeThrough(v);
   }
   
   /** Access the current set of Messages within the given Conversation. */
   public List<String> getVertexList() {
     return data;
   }

   /** Sets the List of Messages stored by this MessageStore. */
   public void setVerticies(List<String> data) {
	   this.data = data;
   }
  
}
