# NOTION

> [Notion](https://www.notion.so/) is an awesome productivity software, please do check it out.

## Contents

1. [Problem Statement](#problem-statement)
2. [Functional Requirements](#functional-requirements)
3. [Extended Requirements](#extended-requirements)
4. [Data Models](#data-models)
   1. Entities
   2. Relationships
   3. Attributes
5. [Tasks](#tasks)
---

## Problem Statement

Build Notion application i.e. all-in-one workspace for notes, docs, wikis, projects, and team collaboration.

---

## Functional Requirements

* Users can create/modify/delete multiple ***pages***.
* A page can have multiple nesting, i.e. a page can contain ***multiple pages***.
* User can mark amy page as favourite.
* User can create/modify/delete a ***block*** of information.
* Every page consists of one or more blocks of information.
* Blocks are stored in an ordered, which can be reordered at any time.
* Blocks can be of different types:
  * **Text Block:** Contains plain texts.
  * **Heading Block:** Contains plain text which is displayed bold and big, there can be different sub-type of headings based on the size of the heading.
  * **Todo Block:** Contains plain text with a checkbox.
  * **List Block:** Contains items list, can be ordered or unordered list.
  * **Page Link Block** Contains the link of the child page.
* Fetch all the pages for a user.
* Fetch all the blocks for a page.

---

## Extended Requirements

* Blocks can have nesting.
* Toggle List BLock
* Search the pages based on the page name.
* Search the content based on any of the content inside the page.
* Users add a comment to a block.
* Users to be able to share pages with other users.
  * Shared pages maybe hai different access, like read only, comment only, etc.
* Page can be shared as a template to other users, i.e. making replica of the page with the new user as the owner.

---

## Data Models

### 1. Entities

- User
- Page
- Block
- Comment _(Future Scope)_
- PageAccess _(Future Scope)_

### 2. Relationships

- User is independent entity
- Page is dependent on User & the Page itself
  - User (1 : N) Pages
  - Page (1 : N) Pages (parent and child pages)
- Block is dependent on Page
  - Page (1 : N) Block 
- Comment is dependent on Block & User
    - User (1 : N) Comments
    - Block (1 : N) Comments
- PageAccess is dependent on Page & User
    - User (1 : N) PageAccess
    - Page (1 : N) PageAccess

### 3. Attributes

- **Users**
  - id _(auto generated)_
  - created_at _(auto generated)_
  - updated_at _(auto generated)_
  - First Name
  - Middle Name
  - Last Name
  - Email Id
  - Password
- **Pages**
  - id _(auto generated)_
  - created_at _(auto generated)_
  - updated_at _(auto generated)_
  - title
  - is_favourite
  - user_id _(foreign key)_
  - parent_page_id _(foreign key)_
- **Blocks**
  - id _(auto generated)_
  - created_at _(auto generated)_
  - updated_at _(auto generated)_
  - block_type
  - value
  - order
  - page_id _(foreign key)_
  - Some Extra info depending on the type of block
- **Comments**
  - id _(auto generated)_
  - created_at _(auto generated)_
  - updated_at _(auto generated)_
  - block_id _(foreign key)_
  - user_id _(foreign key)_
  - comment
- **PageAccess**
  - id _(auto generated)_
  - created_at _(auto generated)_
  - updated_at _(auto generated)_
  - page_id _(foreign key)_
  - user_id _(foreign key)_
  - access_type
 
---

## Tasks

- [X] Base Response
  - [X] Base Rest Response
  - [X] Handle Exception Response
- [ ] Data Models (Entity, Repository, Events, etc.)
  - [X] Page
  - [X] Block
  - [ ] User
  - [ ] Comment
  - [ ] PageAccess
- [ ] CRUD Operations (Controller, Service, Model to Dto Conversion, Exception Handling, etc.)
  - [X] Page
  - [X] Block
  - [ ] User
  - [ ] Comment
  - [ ] PageAccess
- [ ] Documenting the whole project

---












