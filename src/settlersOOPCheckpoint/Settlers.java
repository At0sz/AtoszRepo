/*
 * Copyright © Progmasters (QTC Kft.), 2016-2025.
 * All rights reserved. No part or the whole of this Teaching Material (TM) may be reproduced, copied, distributed,
 * publicly performed, disseminated to the public, adapted or transmitted in any form or by any means, including
 * photocopying, recording, or other electronic or mechanical methods, without the prior written permission of QTC Kft.
 * This TM may only be used for the purposes of teaching exclusively by QTC Kft. and studying exclusively by QTC Kft.’s
 * students and for no other purposes by any parties other than QTC Kft.
 * This TM shall be kept confidential and shall not be made public or made available or disclosed to any unauthorized person.
 * Any dispute or claim arising out of the breach of these provisions shall be governed by and construed in accordance with the laws of Hungary.
 */

package settlersOOPCheckpoint;

import settlersOOPCheckpoint.building.Building;
import settlersOOPCheckpoint.resource.Resource;
import settlersOOPCheckpoint.resource.ResourceType;
import settlersOOPCheckpoint.unit.Archer;
import settlersOOPCheckpoint.unit.Swordsman;
import settlersOOPCheckpoint.unit.Unit;

import java.util.*;

public class Settlers {

    private List<Building> buildingList;
    private List<Unit> unitList;
    private Map<ResourceType, Integer> townTreasury;
    private StringBuilder sb;

    public Settlers() {
        buildingList = new ArrayList<>();
        unitList = new ArrayList<>();
        townTreasury = new EnumMap<>(ResourceType.class);
        townTreasury.put(ResourceType.GOLD, 0);
        townTreasury.put(ResourceType.STEEL, 0);
        sb = new StringBuilder();
    }

    public void addBuilding(Building building) {
        if (building != null) {
            buildingList.add(building);
        }
    }

    public void makeTurn() {
        for (Building building : buildingList) {
            building.advanceTurn();

            if (building.canProduceResource()) {
                Resource producedResource = building.produceResource();
                int currentAmount = townTreasury.getOrDefault(producedResource.getType(), 0);
                townTreasury.put(producedResource.getType(), currentAmount + producedResource.getQuantity());
            }

            if (building.canProduceUnit()) {
                unitList.add(building.produceUnit());
            }
        }
    }

    public String getStatus() {
        sb.setLength(0);

        treasuryStatus();
        buildingStatus();
        unitsStatus();

        return sb.toString();
    }

    private void treasuryStatus() {
        sb.append("Treasury:\n");
        sb.append("--Gold: ").append(townTreasury.get(ResourceType.GOLD)).append("\n");
        sb.append("--Steel: ").append(townTreasury.get(ResourceType.STEEL)).append("\n");
    }

    private void buildingStatus() {
        sb.append("Buildings:\n");
        if (buildingList.isEmpty()) {
            sb.append("N/A\n");
        } else {
            for (Building building : buildingList) {
                sb.append("--").append(building.getClass().getSimpleName()).append(": ")
                        .append(building.getCurrentTurn()).append(" turns (")
                        .append(building.getTurnUntilUnitProduced()).append(" turns until ")
                        .append(building.getProducedUnitName()).append(", ")
                        .append(building.getTurnUntilResourceProduced()).append(" turns until ")
                        .append(building.getProducedResourceName()).append(")\n");
            }
        }
    }

    private void unitsStatus() {
        sb.append("Units:\n");
        if (unitList.isEmpty()) {
            sb.append("N/A");
        } else {
            int archers = 0;
            int swordsmen = 0;

            for (Unit unit : unitList) {
                if (unit instanceof Archer) {
                    archers++;
                } else if (unit instanceof Swordsman) {
                    swordsmen++;
                }
            }

            if (archers > 0) {
                sb.append("--Archer: ").append(archers).append("\n");
            }
            if (swordsmen > 0) {
                sb.append("--Swordsman: ").append(swordsmen);
            }
        }
    }


    //for test purposes
    public int getUnitListSize() {
        return unitList.size();
    }

    public int getBuildingListSize() {
        return buildingList.size();
    }

    public Map<ResourceType, Integer> getTownTreasury() {
        return townTreasury;
    }
}



