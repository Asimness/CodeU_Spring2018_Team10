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
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;

public class UserTest {

  @Test
  public void testCreate() {
    UUID id = UUID.randomUUID();
    String name = "test_username";
    String passwordHash = "$2a$10$bBiLUAVmUFK6Iwg5rmpBUOIBW6rIMhU1eKfi3KR60V9UXaYTwPfHy";
    Instant creation = Instant.now();

    User user = new User(id, name, passwordHash, creation);

    Assert.assertEquals(id, user.getId());
    Assert.assertEquals(name, user.getName());
    Assert.assertEquals(passwordHash, user.getPasswordHash());
    Assert.assertEquals(creation, user.getCreationTime());
    Assert.assertEquals(false, user.getAdmin());
  }
  
  @Test
  public void testCreate_WithAboutMe() {
    UUID id = UUID.randomUUID();
    String name = "test_username";
    String passwordHash = "$2a$10$bBiLUAVmUFK6Iwg5rmpBUOIBW6rIMhU1eKfi3KR60V9UXaYTwPfHy";
    String aboutme = "test_aboutme";
    Instant creation = Instant.now();

    User user = new User(id, name, passwordHash, aboutme, creation);

    Assert.assertEquals(id, user.getId());
    Assert.assertEquals(name, user.getName());
    Assert.assertEquals(passwordHash, user.getPasswordHash());
    Assert.assertEquals(aboutme, user.getAboutMe());
    Assert.assertEquals(creation, user.getCreationTime());
  }
}
