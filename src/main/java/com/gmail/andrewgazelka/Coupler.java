package com.gmail.andrewgazelka;

public class Coupler<A,B> {

    private A first;
    private B second;

    public Coupler(A first, B second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Coupler{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coupler<?, ?> coupler = (Coupler<?, ?>) o;

        if (first != null ? !first.equals(coupler.first) : coupler.first != null) return false;
        return second != null ? second.equals(coupler.second) : coupler.second == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }
}
