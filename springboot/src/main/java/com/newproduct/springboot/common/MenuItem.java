package com.newproduct.springboot.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuItem {

        private String name;
        private String title;
        private String component;
        private String path;
        private List<MenuItem> children;

        // 构造函数和 getter/setter 方法

        public MenuItem() {
            this.children = new ArrayList<>();
        }

        public MenuItem(String name, String title, String component, String path) {
            this.name = name;
            this.title = title;
            this.component = component;
            this.path = path;
            this.children = new ArrayList<>();
        }

        // 添加子菜单项的方法
        public void addChild(MenuItem menuItem) {
            this.children.add(menuItem);
        }
    }



