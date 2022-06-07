package com.bestbuy.model;

public class ServicesPojo {

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;

        public static ServicesPojo getServicesPojo(String name) {
            ServicesPojo servicesPojo =new ServicesPojo();
            servicesPojo.setName(name);
            return servicesPojo;
        }
}
