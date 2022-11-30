package com.hotel.mapper.implementation;

import com.hotel.entity.*;
import com.hotel.mapper.IMapper;
import com.hotel.model.*;
import com.hotel.model.implementation.*;

import java.util.stream.Collectors;
public class Mapper implements IMapper {
    @Override
    public HotelEntity mapIHotelToHotelDTO(IHotel hotel) {
        HotelEntity hotelEntity =  new HotelEntity();
        hotelEntity.setAddress(mapIAddressToAddressDTO(hotel.getAddress()));
        hotelEntity.setRooms(hotel.getRooms().stream().map(this::mapIRoomToRoomDTO).collect(Collectors.toList()));
        hotelEntity.setReservations(hotel.getReservations().stream().map(this::mapIReservationToReservationDTO).collect(Collectors.toList()));
        hotelEntity.setHotelId(hotel.getHotelId());
        hotelEntity.setName(hotel.getName());
        hotelEntity.setPhoneNumber(hotel.getPhoneNumber());
        hotelEntity.setStarRatting(hotel.getStarRatting());
        return hotelEntity;
    }

    public ReservationEntity mapIReservationToReservationDTO(IReservation iReservation){
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setReservationId(iReservation.getReservationId());
        reservationEntity.setState(iReservation.getState().toString());
        reservationEntity.setFromDate(iReservation.getFromDate());
        reservationEntity.setToDate(iReservation.getToDate());
        reservationEntity.setGuestId(iReservation.getGuestId());
        reservationEntity.setRoom( mapIRoomToRoomDTO(iReservation.getRoom()));
        return reservationEntity;
    }

    private RoomEntity mapIRoomToRoomDTO(IRoom iRoom)
    {
        RoomEntity roomEntity =  new RoomEntity();
        roomEntity.setRoomNo(iRoom.getRoomNo());
        roomEntity.setRoomType(iRoom.getRoomType());
        roomEntity.setRentPerNight(iRoom.getRentPerNight());

        return roomEntity;
    }

    private AddressEntity mapIAddressToAddressDTO(IAddress address) {
        AddressEntity addressEntity =  new AddressEntity();
        addressEntity.setAddressId(address.getAddressId());
        addressEntity.setArea(address.getArea());
        addressEntity.setCity(address.getCity());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setPin(address.getPin());
        return addressEntity;
    }

    @Override
    public IHotel mapHotelDTOToIHotel(HotelEntity hotelEntity) {
        IHotel iHotel =  new Hotel();

        iHotel.setHotelId(hotelEntity.getHotelId());
        iHotel.setName(hotelEntity.getName());
        iHotel.setPhoneNumber(hotelEntity.getPhoneNumber());
        iHotel.setStarRatting(hotelEntity.getStarRatting());
        iHotel.setAddress(mapAddressDTOToIAddress(hotelEntity.getAddress()));
        iHotel.setRooms(hotelEntity.getRooms().stream().map(this::mapRoomDTOToIRoom).collect(Collectors.toList()));
        iHotel.setReservations(hotelEntity.getReservations().stream().map(this::mapReservationDTOToIReservation).collect(Collectors.toList()));
        return iHotel;
    }

    private IAddress mapAddressDTOToIAddress(AddressEntity addressEntity){
        IAddress iAddress = new Address();
        iAddress.setAddressId(addressEntity.getAddressId());
        iAddress.setArea(addressEntity.getArea());
        iAddress.setCity(addressEntity.getCity());
        iAddress.setStreet(addressEntity.getStreet());
        iAddress.setPin(addressEntity.getPin());
        return  iAddress;
    }

    private IRoom mapRoomDTOToIRoom(RoomEntity roomEntity){
        IRoom iRoom = new Room();
        if(roomEntity != null) {
            iRoom.setRoomNo(roomEntity.getRoomNo());
            iRoom.setRentPerNight(roomEntity.getRentPerNight());
            iRoom.setRoomType(roomEntity.getRoomType());
        }
        return iRoom;
    }

    public IReservation mapReservationDTOToIReservation(ReservationEntity reservationEntity){
        IReservation iReservation = new Reservation();
        iReservation.setReservationId(reservationEntity.getReservationId());
        iReservation.setState(ReservationStatus.valueOf(reservationEntity.getState()));
        iReservation.setGuestId(reservationEntity.getGuestId());
        iReservation.setRoom(mapRoomDTOToIRoom(reservationEntity.getRoom()));
        iReservation.setFromDate(reservationEntity.getFromDate());
        iReservation.setToDate(reservationEntity.getToDate());
        return iReservation;
    }
}
