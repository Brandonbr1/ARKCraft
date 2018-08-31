package com.arkcraft.client.model;

import com.arkcraft.common.entity.EntityDodo;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDodo extends ModelBase
{
	// All these are parts of the head
	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer RightEyelid;
	ModelRenderer LeftEyelid;
	ModelRenderer TopBeak1;
	ModelRenderer TopBeak2;
	ModelRenderer BotBeak1;
	ModelRenderer BotBeak2;

	// All these are parts of the body
	ModelRenderer UpperBody1;
	ModelRenderer LowerBody;
	ModelRenderer UpperBody2;
	ModelRenderer Neck;
	ModelRenderer Tail1;
	ModelRenderer Tail2;
	ModelRenderer LeftThigh;
	ModelRenderer RightThigh;

	// The wings
	ModelRenderer LeftWing;
	ModelRenderer RightWing;

	// Parts of the left leg
	ModelRenderer LeftLeg;
	ModelRenderer LeftFoot1;
	ModelRenderer LeftFoot2;
	ModelRenderer LeftFoot3;
	ModelRenderer LeftClaw1;
	ModelRenderer LeftClaw2;
	ModelRenderer LeftClaw3;

	// Parts of the right leg
	ModelRenderer RightLeg;
	ModelRenderer RightFoot1;
	ModelRenderer RightFoot2;
	ModelRenderer RightFoot3;
	ModelRenderer RightClaw1;
	ModelRenderer RightClaw2;
	ModelRenderer RightClaw3;

	// The pack
	ModelRenderer Strap1;
	ModelRenderer Box;
	ModelRenderer Strap2;
	ModelRenderer Lid1;
	ModelRenderer Lid2;
	ModelRenderer Lid3;
	ModelRenderer Lid4;
	ModelRenderer String1;
	ModelRenderer NeckStrap;
	ModelRenderer Berry1;
	ModelRenderer Berry2;
	ModelRenderer Berry3;
	ModelRenderer leaf1;
	ModelRenderer leaf2;
	ModelRenderer leaf3;
	ModelRenderer Stick1;
	ModelRenderer Stick2;
	ModelRenderer Stick3;
	ModelRenderer StickStrap1;
	ModelRenderer StickStrap2;
	ModelRenderer String2;
	ModelRenderer String3;
	ModelRenderer String4;
	ModelRenderer leaf4;
	ModelRenderer leaf5;

	public ModelDodo()
	{
		textureWidth = 128;
		textureHeight = 128;

		// Head parts
		Head1 = new ModelRenderer(this, 12, 24);
		Head1.addBox(-1.7F, -3.2F, -0.5F, 3, 5, 4);
		Head1.setRotationPoint(0F, 12.8F, 0F);
		Head1.setTextureSize(128, 128);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 12, 24);
		Head2.addBox(-1.3F, -3.2F, -0.5F, 3, 5, 4);
		Head2.setRotationPoint(0F, 12.8F, 0F);
		Head2.setTextureSize(128, 128);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);
		RightEyelid = new ModelRenderer(this, 0, 21);
		RightEyelid.addBox(-2F, -1.4F, 0.5F, 1, 1, 1);
		RightEyelid.setRotationPoint(0F, 12.8F, 0F);
		RightEyelid.setTextureSize(128, 128);
		RightEyelid.mirror = true;
		setRotation(RightEyelid, 0F, 0F, 0.2792527F);
		LeftEyelid = new ModelRenderer(this, 0, 21);
		LeftEyelid.addBox(1F, -1.4F, 0.5F, 1, 1, 1);
		LeftEyelid.setRotationPoint(0F, 12.8F, 0F);
		LeftEyelid.setTextureSize(128, 128);
		LeftEyelid.mirror = true;
		setRotation(LeftEyelid, 0F, 0F, -0.2792527F);
		TopBeak1 = new ModelRenderer(this, 0, 0);
		TopBeak1.addBox(-1F, -2F, -5F, 2, 2, 5);
		TopBeak1.setRotationPoint(0F, 12.8F, 0F);
		TopBeak1.setTextureSize(128, 128);
		TopBeak1.mirror = true;
		setRotation(TopBeak1, 0F, 0F, 0F);
		TopBeak2 = new ModelRenderer(this, 0, 7);
		TopBeak2.addBox(-1F, -0.4F, -5F, 2, 1, 2);
		TopBeak2.setRotationPoint(0F, 12.8F, 0F);
		TopBeak2.setTextureSize(128, 128);
		TopBeak2.mirror = true;
		setRotation(TopBeak2, 0F, 0F, 0F);
		BotBeak1 = new ModelRenderer(this, 0, 10);
		BotBeak1.addBox(-0.7F, -0.5F, -4F, 1, 1, 4);
		BotBeak1.setRotationPoint(0F, 12.8F, 0F);
		BotBeak1.setTextureSize(128, 128);
		BotBeak1.mirror = true;
		setRotation(BotBeak1, 0F, 0F, 0F);
		BotBeak2 = new ModelRenderer(this, 0, 10);
		BotBeak2.addBox(-0.3F, -0.5F, -4F, 1, 1, 4);
		BotBeak2.setRotationPoint(0F, 12.8F, 0F);
		BotBeak2.setTextureSize(128, 128);
		BotBeak2.mirror = true;
		setRotation(BotBeak2, 0F, 0F, 0F);
		convertToChild(Head1, BotBeak2);
		convertToChild(Head1, BotBeak1);
		convertToChild(Head1, TopBeak2);
		convertToChild(Head1, TopBeak1);
		convertToChild(Head1, LeftEyelid);
		convertToChild(Head1, RightEyelid);
		convertToChild(Head1, Head2);

		// Parts of the body
		UpperBody1 = new ModelRenderer(this, 0, 33);
		UpperBody1.addBox(-2.2F, -1.5F, -0.5F, 4, 5, 5);
		UpperBody1.setRotationPoint(0F, 17.8F, 0.5F);
		UpperBody1.setTextureSize(128, 128);
		UpperBody1.mirror = true;
		setRotation(UpperBody1, 0.1745329F, 0F, 0F);
		LowerBody = new ModelRenderer(this, 0, 43);
		LowerBody.addBox(-2F, -2.1F, 4F, 4, 4, 3);
		LowerBody.setRotationPoint(0F, 17.8F, 0.5F);
		LowerBody.setTextureSize(128, 128);
		LowerBody.mirror = true;
		setRotation(LowerBody, 0F, 0F, 0F);
		UpperBody2 = new ModelRenderer(this, 0, 33);
		UpperBody2.addBox(-1.8F, -1.5F, -0.5F, 4, 5, 5);
		UpperBody2.setRotationPoint(0F, 17.8F, 0.5F);
		UpperBody2.setTextureSize(128, 128);
		UpperBody2.mirror = true;
		setRotation(UpperBody2, 0.1745329F, 0F, 0F);
		Neck = new ModelRenderer(this, 0, 23);
		Neck.addBox(-1.5F, -4.5F, -1.5F, 3, 7, 3);
		Neck.setRotationPoint(0F, 17.8F, 1F);
		Neck.setTextureSize(128, 128);
		Neck.mirror = true;
		setRotation(Neck, -0.1396263F, 0F, 0F);
		Tail1 = new ModelRenderer(this, 0, 50);
		Tail1.addBox(-1.7F, -3.1F, 6F, 3, 2, 3);
		Tail1.setRotationPoint(0F, 17.8F, 0.5F);
		Tail1.setTextureSize(128, 128);
		Tail1.mirror = true;
		setRotation(Tail1, -0.1745329F, 0F, 0F);
		Tail2 = new ModelRenderer(this, 0, 50);
		Tail2.addBox(-1.3F, -3.1F, 6F, 3, 2, 3);
		Tail2.setRotationPoint(0F, 17.8F, 0.5F);
		Tail2.setTextureSize(128, 128);
		Tail2.mirror = true;
		setRotation(Tail2, -0.1745329F, 0F, 0F);
		LeftThigh = new ModelRenderer(this, 18, 33);
		LeftThigh.addBox(0.2F, 3.5F, 2.5F, 2, 1, 2);
		LeftThigh.setRotationPoint(0F, 17.8F, 0.5F);
		LeftThigh.setTextureSize(128, 128);
		LeftThigh.mirror = true;
		setRotation(LeftThigh, 0.1745329F, 0F, 0F);
		RightThigh = new ModelRenderer(this, 18, 33);
		RightThigh.addBox(-2.2F, 3.5F, 2.5F, 2, 1, 2);
		RightThigh.setRotationPoint(0F, 17.8F, 0.5F);
		RightThigh.setTextureSize(128, 128);
		RightThigh.mirror = true;
		setRotation(RightThigh, 0.1745329F, 0F, 0F);
		convertToChild(UpperBody1, RightThigh);
		convertToChild(UpperBody1, LeftThigh);
		convertToChild(UpperBody1, Tail2);
		convertToChild(UpperBody1, Tail1);
		convertToChild(UpperBody1, Neck);
		convertToChild(UpperBody1, UpperBody2);
		convertToChild(UpperBody1, LowerBody);

		// Left wing
		LeftWing = new ModelRenderer(this, 0, 55);
		LeftWing.addBox(0.7F, -0.5F, -1F, 1, 3, 4);
		LeftWing.setRotationPoint(1.5F, 16.8F, 2F);
		LeftWing.setTextureSize(128, 128);
		LeftWing.mirror = true;
		setRotation(LeftWing, 0F, 0F, 0F);

		// Right wing
		RightWing = new ModelRenderer(this, 0, 55);
		RightWing.addBox(-1.7F, -0.5F, -1F, 1, 3, 4);
		RightWing.setRotationPoint(-1.5F, 16.8F, 2F);
		RightWing.setTextureSize(128, 128);
		RightWing.mirror = true;
		setRotation(RightWing, 0F, 0F, 0F);

		// Left leg parts
		LeftLeg = new ModelRenderer(this, 18, 36);
		LeftLeg.addBox(-0.5F, 0F, 0F, 1, 3, 1);
		LeftLeg.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftLeg.setTextureSize(128, 128);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, -0.2617994F, 0F, 0F);
		LeftFoot1 = new ModelRenderer(this, 18, 40);
		LeftFoot1.addBox(-0.5F, 2.2F, -2F, 1, 1, 2);
		LeftFoot1.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftFoot1.setTextureSize(128, 128);
		LeftFoot1.mirror = true;
		setRotation(LeftFoot1, 0F, 0.3490659F, 0F);
		LeftFoot2 = new ModelRenderer(this, 18, 40);
		LeftFoot2.addBox(-0.5F, 2.2F, -2F, 1, 1, 2);
		LeftFoot2.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftFoot2.setTextureSize(128, 128);
		LeftFoot2.mirror = true;
		setRotation(LeftFoot2, 0F, -0.3490659F, 0F);
		LeftFoot3 = new ModelRenderer(this, 18, 43);
		LeftFoot3.addBox(-0.5F, 2.2F, 0F, 1, 1, 1);
		LeftFoot3.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftFoot3.setTextureSize(128, 128);
		LeftFoot3.mirror = true;
		setRotation(LeftFoot3, 0F, 0F, 0F);
		LeftClaw1 = new ModelRenderer(this, 18, 45);
		LeftClaw1.addBox(0F, 2.2F, -2.5F, 0, 1, 1);
		LeftClaw1.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftClaw1.setTextureSize(128, 128);
		LeftClaw1.mirror = true;
		setRotation(LeftClaw1, 0F, 0.3490659F, 0F);
		LeftClaw2 = new ModelRenderer(this, 18, 45);
		LeftClaw2.addBox(0F, 2.2F, -2.5F, 0, 1, 1);
		LeftClaw2.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftClaw2.setTextureSize(128, 128);
		LeftClaw2.mirror = true;
		setRotation(LeftClaw2, 0F, -0.3490659F, 0F);
		LeftClaw3 = new ModelRenderer(this, 18, 45);
		LeftClaw3.addBox(0F, 2.2F, 0.5F, 0, 1, 1);
		LeftClaw3.setRotationPoint(1.2F, 20.8F, 4.5F);
		LeftClaw3.setTextureSize(128, 128);
		LeftClaw3.mirror = true;
		setRotation(LeftClaw3, 0F, 0F, 0F);

		convertToChild(LeftLeg, LeftClaw3);
		convertToChild(LeftLeg, LeftClaw2);
		convertToChild(LeftLeg, LeftClaw1);
		convertToChild(LeftLeg, LeftFoot3);
		convertToChild(LeftLeg, LeftFoot2);
		convertToChild(LeftLeg, LeftFoot1);

		// Right leg parts
		RightLeg = new ModelRenderer(this, 18, 36);
		RightLeg.addBox(-0.5F, 0F, 0F, 1, 3, 1);
		RightLeg.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightLeg.setTextureSize(128, 128);
		RightLeg.mirror = true;
		setRotation(RightLeg, -0.2617994F, 0F, 0F);
		RightFoot1 = new ModelRenderer(this, 18, 40);
		RightFoot1.addBox(-0.5F, 2.2F, -2F, 1, 1, 2);
		RightFoot1.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightFoot1.setTextureSize(128, 128);
		RightFoot1.mirror = true;
		setRotation(RightFoot1, 0F, 0.3490659F, 0F);
		RightFoot2 = new ModelRenderer(this, 18, 40);
		RightFoot2.addBox(-0.5F, 2.2F, -2F, 1, 1, 2);
		RightFoot2.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightFoot2.setTextureSize(128, 128);
		RightFoot2.mirror = true;
		setRotation(RightFoot2, 0F, -0.3490659F, 0F);
		RightFoot3 = new ModelRenderer(this, 18, 43);
		RightFoot3.addBox(-0.5F, 2.2F, 0F, 1, 1, 1);
		RightFoot3.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightFoot3.setTextureSize(128, 128);
		RightFoot3.mirror = true;
		setRotation(RightFoot3, 0F, 0F, 0F);
		RightClaw1 = new ModelRenderer(this, 18, 45);
		RightClaw1.addBox(0F, 2.2F, -2.5F, 0, 1, 1);
		RightClaw1.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightClaw1.setTextureSize(128, 128);
		RightClaw1.mirror = true;
		setRotation(RightClaw1, 0F, 0.3490659F, 0F);
		RightClaw2 = new ModelRenderer(this, 18, 45);
		RightClaw2.addBox(0F, 2.2F, -2.5F, 0, 1, 1);
		RightClaw2.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightClaw2.setTextureSize(128, 128);
		RightClaw2.mirror = true;
		setRotation(RightClaw2, 0F, -0.3490659F, 0F);
		RightClaw3 = new ModelRenderer(this, 18, 45);
		RightClaw3.addBox(0F, 2.2F, 0.5F, 0, 1, 1);
		RightClaw3.setRotationPoint(-1.2F, 20.8F, 4.5F);
		RightClaw3.setTextureSize(128, 128);
		RightClaw3.mirror = true;
		setRotation(RightClaw3, 0F, 0F, 0F);
		convertToChild(RightLeg, RightClaw3);
		convertToChild(RightLeg, RightClaw2);
		convertToChild(RightLeg, RightClaw1);
		convertToChild(RightLeg, RightFoot3);
		convertToChild(RightLeg, RightFoot2);
		convertToChild(RightLeg, RightFoot1);

		// Pack lid
		Lid1 = new ModelRenderer(this, 14, 14);
		Lid1.addBox(-2.7F, -4.5F, 2.9F, 5, 1, 4);
		Lid1.setRotationPoint(0F, 17.8F, 1.5F);
		Lid1.setTextureSize(128, 128);
		Lid1.mirror = true;
		setRotation(Lid1, 0.1745329F, 0F, 0F);
		Lid2 = new ModelRenderer(this, 14, 14);
		Lid2.addBox(-2.3F, -4.5F, 2.9F, 5, 1, 4);
		Lid2.setRotationPoint(0F, 17.8F, 1.5F);
		Lid2.setTextureSize(128, 128);
		Lid2.mirror = true;
		setRotation(Lid2, 0.1745329F, 0F, 0F);
		convertToChild(Lid1, Lid2);
		Lid3 = new ModelRenderer(this, 14, 19);
		Lid3.addBox(-2.7F, -4.5F, 6.5F, 5, 2, 1);
		Lid3.setRotationPoint(0F, 17.8F, 1.5F);
		Lid3.setTextureSize(128, 128);
		Lid3.mirror = true;
		setRotation(Lid3, 0.1745329F, 0F, 0F);
		convertToChild(Lid1, Lid3);
		Lid4 = new ModelRenderer(this, 14, 19);
		Lid4.addBox(-2.3F, -4.5F, 6.5F, 5, 2, 1);
		Lid4.setRotationPoint(0F, 17.8F, 1.5F);
		Lid4.setTextureSize(128, 128);
		Lid4.mirror = true;
		setRotation(Lid4, 0.1745329F, 0F, 0F);
		convertToChild(Lid1, Lid4);

		// Pack (everything but lid)
		Box = new ModelRenderer(this, 14, 8);
		Box.addBox(-2.5F, -4F, 2.2F, 5, 2, 4);
		Box.setRotationPoint(0F, 17.8F, 1.5F);
		Box.setTextureSize(128, 128);
		Box.mirror = true;
		setRotation(Box, 0F, 0F, 0F);
		NeckStrap = new ModelRenderer(this, 26, 24);
		NeckStrap.addBox(-2F, -3F, -1.5F, 4, 1, 5);
		NeckStrap.setRotationPoint(0F, 18.8F, 1.5F);
		NeckStrap.setTextureSize(128, 128);
		NeckStrap.mirror = true;
		setRotation(NeckStrap, 0.3839724F, 0F, 0F);
		convertToChild(Box, NeckStrap);
		Strap1 = new ModelRenderer(this, 26, 30);
		Strap1.addBox(-2.6F, -5.8F, 1.5F, 5, 9, 1);
		Strap1.setRotationPoint(0F, 17.8F, 1.5F);
		Strap1.setTextureSize(128, 128);
		Strap1.mirror = true;
		setRotation(Strap1, -0.4363323F, 0F, 0F);
		convertToChild(Box, Strap1);
		Strap2 = new ModelRenderer(this, 26, 30);
		Strap2.addBox(-2.4F, -5.8F, 1.5F, 5, 9, 1);
		Strap2.setRotationPoint(0F, 17.8F, 1.5F);
		Strap2.setTextureSize(128, 128);
		Strap2.mirror = true;
		setRotation(Strap2, -0.4363323F, 0F, 0F);
		convertToChild(Box, Strap2);
		String1 = new ModelRenderer(this, 26, 19);
		String1.addBox(-0.5F, -3.8F, 6.9F, 1, 3, 1);
		String1.setRotationPoint(0F, 17.8F, 1.5F);
		String1.setTextureSize(128, 128);
		String1.mirror = true;
		setRotation(String1, 0.2617994F, 0F, 0F);
		convertToChild(Box, String1);
		Berry1 = new ModelRenderer(this, 4, 17);
		Berry1.addBox(2.6F, 0F, 5.3F, 1, 1, 1);
		Berry1.setRotationPoint(0F, 17.8F, 1.5F);
		Berry1.setTextureSize(128, 128);
		Berry1.mirror = true;
		setRotation(Berry1, 0F, -0.1047198F, -0.2094395F);
		convertToChild(Box, Berry1);
		Berry2 = new ModelRenderer(this, 0, 15);
		Berry2.addBox(2.5F, -1F, 5F, 1, 1, 1);
		Berry2.setRotationPoint(0F, 17.8F, 1.5F);
		Berry2.setTextureSize(128, 128);
		Berry2.mirror = true;
		setRotation(Berry2, 0F, -0.0349066F, -0.2617994F);
		convertToChild(Box, Berry2);
		Berry3 = new ModelRenderer(this, 4, 15);
		Berry3.addBox(2.6F, -0.5F, 4.3F, 1, 1, 1);
		Berry3.setRotationPoint(0F, 17.8F, 1.5F);
		Berry3.setTextureSize(128, 128);
		Berry3.mirror = true;
		setRotation(Berry3, 0F, -0.0523599F, -0.1570796F);
		convertToChild(Box, Berry3);
		leaf1 = new ModelRenderer(this, 0, 17);
		leaf1.addBox(3F, -1.5F, 5F, 0, 2, 1);
		leaf1.setRotationPoint(0F, 17.8F, 1.5F);
		leaf1.setTextureSize(128, 128);
		leaf1.mirror = true;
		setRotation(leaf1, 0.122173F, 0F, -0.2617994F);
		convertToChild(Box, leaf1);
		leaf2 = new ModelRenderer(this, 0, 17);
		leaf2.addBox(3F, -0.5F, 5.5F, 0, 2, 1);
		leaf2.setRotationPoint(0F, 17.8F, 1.5F);
		leaf2.setTextureSize(128, 128);
		leaf2.mirror = true;
		setRotation(leaf2, 0.122173F, -0.0523599F, -0.2617994F);
		convertToChild(Box, leaf2);
		leaf3 = new ModelRenderer(this, 0, 17);
		leaf3.addBox(3F, -0.5F, 4.5F, 0, 3, 1);
		leaf3.setRotationPoint(0F, 17.8F, 1.5F);
		leaf3.setTextureSize(128, 128);
		leaf3.mirror = true;
		setRotation(leaf3, 0.122173F, -0.0523599F, -0.2617994F);
		convertToChild(Box, leaf3);
		Stick1 = new ModelRenderer(this, 10, 10);
		Stick1.addBox(-3.5F, -6F, 5F, 1, 8, 1);
		Stick1.setRotationPoint(0F, 17.8F, 1.5F);
		Stick1.setTextureSize(128, 128);
		Stick1.mirror = true;
		setRotation(Stick1, 0.2792527F, 0F, 0F);
		convertToChild(Box, Stick1);
		Stick2 = new ModelRenderer(this, 10, 19);
		Stick2.addBox(-3.4F, -2F, 5.6F, 1, 1, 1);
		Stick2.setRotationPoint(0F, 17.8F, 1.5F);
		Stick2.setTextureSize(128, 128);
		Stick2.mirror = true;
		setRotation(Stick2, 0.6108652F, 0F, 0F);
		convertToChild(Box, Stick2);
		Stick3 = new ModelRenderer(this, 10, 21);
		Stick3.addBox(-3.4F, -3F, 4.8F, 1, 1, 1);
		Stick3.setRotationPoint(0F, 17.8F, 1.5F);
		Stick3.setTextureSize(128, 128);
		Stick3.mirror = true;
		setRotation(Stick3, -0.1745329F, 0F, 0F);
		convertToChild(Box, Stick3);
		StickStrap1 = new ModelRenderer(this, 26, 40);
		StickStrap1.addBox(-3.6F, -2F, 5.1F, 2, 1, 1);
		StickStrap1.setRotationPoint(0F, 17.8F, 1.5F);
		StickStrap1.setTextureSize(128, 128);
		StickStrap1.mirror = true;
		setRotation(StickStrap1, 0.2792527F, 0F, 0F);
		convertToChild(Box, StickStrap1);
		StickStrap2 = new ModelRenderer(this, 26, 40);
		StickStrap2.addBox(-3.6F, -2F, 4.9F, 2, 1, 1);
		StickStrap2.setRotationPoint(0F, 17.8F, 1.5F);
		StickStrap2.setTextureSize(128, 128);
		StickStrap2.mirror = true;
		setRotation(StickStrap2, 0.2792527F, 0F, 0F);
		convertToChild(Box, StickStrap2);
		String2 = new ModelRenderer(this, 14, 3);
		String2.addBox(-0.5F, -4.6F, 2.8F, 1, 1, 4);
		String2.setRotationPoint(0F, 17.8F, 1.5F);
		String2.setTextureSize(128, 128);
		String2.mirror = true;
		setRotation(String2, 0.1745329F, 0F, 0F);
		convertToChild(Box, String2);
		String3 = new ModelRenderer(this, 14, 1);
		String3.addBox(-0.5F, -4.6F, 6.5F, 1, 1, 1);
		String3.setRotationPoint(0F, 17.8F, 1.5F);
		String3.setTextureSize(128, 128);
		String3.mirror = true;
		setRotation(String3, 0.1745329F, 0F, 0F);
		convertToChild(Box, String3);
		String4 = new ModelRenderer(this, 14, 1);
		String4.addBox(-0.5F, -3.6F, 2.8F, 1, 1, 1);
		String4.setRotationPoint(0F, 17.8F, 1.5F);
		String4.setTextureSize(128, 128);
		String4.mirror = true;
		setRotation(String4, 0.1745329F, 0F, 0F);
		convertToChild(Box, String4);
		leaf4 = new ModelRenderer(this, -2, 18);
		leaf4.addBox(0.5F, -4F, 5.5F, 1, 0, 2);
		leaf4.setRotationPoint(0F, 16.8F, 1.5F);
		leaf4.setTextureSize(128, 128);
		leaf4.mirror = true;
		setRotation(leaf4, 0F, -0.7853982F, 0F);
		convertToChild(Box, leaf4);
		leaf5 = new ModelRenderer(this, -2, 18);
		leaf5.addBox(-5.766667F, -5F, 2F, 1, 0, 2);
		leaf5.setRotationPoint(0F, 16.8F, 1.5F);
		leaf5.setTextureSize(128, 128);
		leaf5.mirror = true;
		setRotation(leaf5, 0.1745329F, 0.7853982F, 0F);
		convertToChild(Box, leaf5);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);

		if (this.isChild)
		{
			float scaling = 2.0F;
			GlStateManager.pushMatrix();
			GlStateManager.scale(1.0F / scaling, 1.0F / scaling, 1.0F / scaling);
			GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
			Head1.render(f5);
			UpperBody1.render(f5);
			LeftWing.render(f5);
			RightWing.render(f5);
			LeftLeg.render(f5);
			RightLeg.render(f5);
			GlStateManager.popMatrix();
			GlStateManager.scale(1, 1, 1);
		}
		else
		{
			// Adult
			Head1.render(f5);
			UpperBody1.render(f5);
			LeftWing.render(f5);
			RightWing.render(f5);
			LeftLeg.render(f5);
			RightLeg.render(f5);
			if (entity != null && ((EntityDodo) entity).isChested())
			{
				Box.render(f5);
				Lid1.render(f5);
			}
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);

		Head1.rotateAngleX = f4 / (180F / (float) Math.PI);
		Head1.rotateAngleY = f4 / (180F / (float) Math.PI);

		LeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		RightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;

		LeftWing.rotateAngleZ = -f2;
		RightWing.rotateAngleZ = f2;

		// Blink eyes
		if (e != null && ((EntityDodo) e).isEyesOpen())
		{
			RightEyelid.rotateAngleZ = 0.2792527F;
			LeftEyelid.rotateAngleZ = -0.2792527F;
		}
		else
		{
			// Close eyes
			RightEyelid.rotateAngleZ = 0;
			LeftEyelid.rotateAngleZ = 0;
			// LogHelper.info("ModelDodo: Closed eyes");
		}
	}

	// Use this for parts that have a common rotation point, so they can be
	// rendered together with a single call to the render function
	protected void convertToChild(ModelRenderer parParent, ModelRenderer parChild)
	{
		// move child rotation point to be relative to parent
		parChild.rotationPointX -= parParent.rotationPointX;
		parChild.rotationPointY -= parParent.rotationPointY;
		parChild.rotationPointZ -= parParent.rotationPointZ;
		// make rotations relative to parent
		parChild.rotateAngleX -= parParent.rotateAngleX;
		parChild.rotateAngleY -= parParent.rotateAngleY;
		parChild.rotateAngleZ -= parParent.rotateAngleZ;
		// create relationship
		parParent.addChild(parChild);
	}
}