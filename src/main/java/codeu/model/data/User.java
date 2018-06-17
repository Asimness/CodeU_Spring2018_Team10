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

package codeu.model.data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/** Class representing a registered user. */
public class User {
  private final UUID id;
  private final String name;
  private final String passwordHash;
  private String aboutme;
  private final Instant creation;
  private boolean admin;
  private String gender;
  private int age;
  private String ethnicity;
  private List<String> friends;

  /**
   * Constructs a new User.
   *
   * @param id the ID of this User
   * @param name the username of this User
   * @param passwordHash the password of this User
   * @param aboutme the about me of this user
   * @param creation the creation time of this User
   */
  public User(UUID id, String name, String passwordHash, String aboutme ,Instant creation) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.creation = creation;
    this.aboutme = aboutme;
    friends = new ArrayList();
  }

  /**
   * Constructs a new User.
   *
   * @param id the ID of this User
   * @param name the username of this User
   * @param passwordHash the password of this User
   * @param creation the creation time of this User
   */
  public User(UUID id, String name, String passwordHash, Instant creation) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.aboutme = "";
    this.creation = creation;
    admin = false;
    friends = new ArrayList<String>();
  }

  public User(UUID id, String name, String passwordHash, String aboutme, Instant creation, String gender, int age, String ethnicity) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.aboutme = aboutme;
    this.creation = creation;
    admin = false;
    this.gender = gender;
    this.age = age;
    this.ethnicity = ethnicity;
    friends = new ArrayList<String>();
  }

  public User(UUID id, String name, String passwordHash, Instant creation, boolean admin) {
    this.id = id;
    this.name = name;
    this.passwordHash = passwordHash;
    this.creation = creation;
    this.admin = admin;
    friends = new ArrayList<String>();
  }

  /** Returns the ID of this User. */
  public UUID getId() {
    return id;
  }

  /** Returns the username of this User. */
  public String getName() {
    return name;
  }

  /** Returns the password hash of this User. */
  public String getPasswordHash() {
    return passwordHash;
  }

  /** Returns the aboutme of this User. */
  public String getAboutMe() {
    return aboutme;
  }

  /** Sets the aboutme of this user. */
  public void setAboutMe(String aboutme) {
    this.aboutme = aboutme;
  }

  /** Returns the creation time of this User. */
  public Instant getCreationTime() {
    return creation;
  }

  /** Returns if the user is an admin */
  public boolean getAdmin() {
    return admin;
  }

  /** Returns a list of the user's friends*/
  public List<String> getFriends() { return friends; }

  // To set admin afterwards
  public void setAdmin(boolean newStatus) {
    admin = newStatus;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getEthnicity() {
    return ethnicity;
  }

  public void setEthnicity(String ethnicity) {
    this.ethnicity = ethnicity;
  }

  public void addFriend(String username) {
    friends.add(username);
  }
}
