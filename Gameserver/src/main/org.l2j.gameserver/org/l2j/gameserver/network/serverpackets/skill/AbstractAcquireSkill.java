/*
 * Copyright © 2019-2021 L2JOrg
 *
 * This file is part of the L2JOrg project.
 *
 * L2JOrg is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * L2JOrg is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.l2j.gameserver.network.serverpackets.skill;

import io.github.joealisson.mmocore.WritableBuffer;
import org.l2j.gameserver.model.SkillLearn;
import org.l2j.gameserver.network.serverpackets.ServerPacket;

/**
 * @author JoeAlisson
 */
public abstract class AbstractAcquireSkill extends ServerPacket {

    protected void writeSkillLearn(SkillLearn skillLearn, WritableBuffer buffer) {
        buffer.writeInt(skillLearn.getSkillId());
        buffer.writeShort(skillLearn.getSkillLevel());
        buffer.writeLong(skillLearn.getLevelUpSp());
        buffer.writeByte(skillLearn.requiredLevel());
        buffer.writeByte(0x00); // dual class level
        buffer.writeByte(skillLearn.getSkillLevel() == 1); // new skill

        buffer.writeByte(skillLearn.getRequiredItems().size());
        for (var item : skillLearn.getRequiredItems()) {
            buffer.writeInt(item.getId());
            buffer.writeLong(item.getCount());
        }

        buffer.writeByte(skillLearn.getReplacedSkills().size());
        for (var skill : skillLearn.getReplacedSkills()) {
            buffer.writeInt(skill.getId());
            buffer.writeShort(skill.getLevel());
        }
    }
}
