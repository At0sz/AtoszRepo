package Targyalo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Office {

    private List<MeetingRoom> meetingRooms = new ArrayList<>(Arrays.asList(
            new MeetingRoom("Tárgyaló 1", 3, 3),
            new MeetingRoom("Tárgyaló 2", 3, 4),
            new MeetingRoom("Tárgyaló 3", 3, 4),
            new MeetingRoom("Kis terem", 2, 3),
            new MeetingRoom("Konferencia terem", 10, 10)
    ));


    public void addMeetingRoom(String name, int width, int length) {
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        meetingRooms.add(meetingRoom);
    }

    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public List<MeetingRoom> getMeetingRoomsinReverseOrder() {
        List<MeetingRoom> result = new ArrayList<>();
        for (int i = (meetingRooms.size() - 1); i >= 0; i--) {
            result.add(meetingRooms.get(i));
        }
        return result;
    }

    public List<MeetingRoom> getEverySecondMeetingRoom(int number) {
        List<MeetingRoom> result = new ArrayList<>();

        if (number == 1) {
            for (int i = 0; i < meetingRooms.size(); i = i + 2) {
                result.add(meetingRooms.get(i));
            }
        }
        if (number == 2) {
            for (int i = 1; i < meetingRooms.size(); i = i + 2) {
                result.add(meetingRooms.get(i));
            }

        }
        return result;
    }

    public MeetingRoom getMeetingRoomGivenName(String name) {
        for (MeetingRoom meetingRoom : meetingRooms) {
            if(meetingRoom.getName().equalsIgnoreCase(name)) {
                return meetingRoom;
            }
        }
        return null;
    }

    public List<MeetingRoom> getMeetingRoomsWithGivenNamePart(String namePart){
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            if(meetingRoom.getName().contains(namePart)) {
                result.add(meetingRoom);
            }
        }
        return result;
    }

    public List<MeetingRoom> getMeetingRoomsWithGivenAreaLargerThan(int number){
        List<MeetingRoom> result = new ArrayList<>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            if(meetingRoom.getArea() > number) {
                result.add(meetingRoom);
            }
        }
        return result;
    }

}
