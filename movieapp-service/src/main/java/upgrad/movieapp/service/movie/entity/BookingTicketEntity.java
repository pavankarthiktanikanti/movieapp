
package upgrad.movieapp.service.movie.entity;

import static upgrad.movieapp.service.common.entity.Entity.SCHEMA;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import upgrad.movieapp.service.common.entity.Identifier;
import upgrad.movieapp.service.common.entity.ImmutableEntity;
import upgrad.movieapp.service.common.entity.UniversalUniqueIdentifier;
import upgrad.movieapp.service.common.entity.ext.EntityEqualsBuilder;
import upgrad.movieapp.service.common.entity.ext.EntityHashCodeBuilder;

@Entity
@Table(name = "BOOKING_TICKETS", schema = SCHEMA)
public class BookingTicketEntity extends ImmutableEntity implements Identifier<Long>, UniversalUniqueIdentifier<String>, Serializable {

    private static final long serialVersionUID = 7932286494206403090L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "UUID")
    @Size(max = 36)
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "BOOKING_ID")
    private ShowBookingEntity booking;

    @Column(name = "TICKET_NUMBER")
    @Size(max = 3)
    private String ticketNumber;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUuid() {
        return uuid;
    }

    public ShowBookingEntity getBooking() {
        return booking;
    }

    public void setBooking(ShowBookingEntity booking) {
        this.booking = booking;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    @Override
    public boolean equals(Object obj) {
        return new EntityEqualsBuilder<Long>().equalsById(this, obj);
    }

    @Override
    public int hashCode() {
        return new EntityHashCodeBuilder<Long>().hashCodeById(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @PrePersist
    public void prePersist() {
        this.uuid = UUID.randomUUID().toString();
        super.prePersist();
    }

}
