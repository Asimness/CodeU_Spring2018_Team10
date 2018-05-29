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

/** Class representing an activity. */
public class Activity {
  private final UUID id;
  private final String content;
  private final Instant creation;
  
  /**
   * Constructs a new User.
   *
   * @param id the ID of this Activity
   * @param content the content of this Activity
   * @param creation the creation time of this activity
   */
  public Activity(UUID id, String content, Instant creation) {
	 this.id = id;
	 this.content = content;
	 this.creation = creation;
  }
  
  /** Returns the ID of this Activity. */
  public UUID getId() {
    return id;
  }
  
  /** Returns the text content of this Activity. */
  public String getContent() {
    return content;
  }
  
  /** Returns the creation time of this Activity. */
  public Instant getCreationTime() {
    return creation;
  }
}

