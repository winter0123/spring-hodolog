package com.hodolog.api.domain;

import lombok.Getter;

@Getter
public class PostEditor {

   private String title = null;
   private String content = null;

   public PostEditor(String title, String content) {
       this.title = title;
       this.content = content;
   }

   public static PostEditor.PostEditorBuilder builder() {
       return new PostEditor.PostEditorBuilder();
   }

   public static class  PostEditorBuilder {
       private String title;
       private String content;

       PostEditorBuilder() {
       }

       public PostEditor.PostEditorBuilder title(final String title) {
           if (title != null) {
               this.title = title;
           }
           return this;
       }

       public PostEditor.PostEditorBuilder content(final String content) {
           if (content != null) {
               this.content = content;
           }
           return this;
       }


       public PostEditor build() {
           return new PostEditor(this.title, this.content);
       }

       public String toString() {
           return "PostEditor.PostEditorBuider(title=" + this.title;
       }
   }

}
