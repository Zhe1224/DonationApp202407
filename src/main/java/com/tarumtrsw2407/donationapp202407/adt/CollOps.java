/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.adt;

/**
 *
 * @author vk_wong
 */
public enum CollOps{
    append, filter, update, delete, leave;
    @Override
        public final String toString(){
            switch (this) {
            case append:
                return "Append";
            case filter:
                return "Filter";
            case update:
                return "Update";
            case delete:
                return "Delete";
            case leave:
                return "Leave";
            default:
                return null;
            }
    }
}