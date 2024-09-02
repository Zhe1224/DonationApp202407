/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tarumtrsw2407.donationapp202407.adt;

/**
 *
 * @author vk_wong
 */
public enum DonorBody {
    Person,Org,Null;
    @Override
    public final String toString(){
        switch (this) {
            case Person:return "Person";
            case Org:return "Organisation";
            case Null:return "?";
            default:return null;
        }
    }
}
