<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
**Table of Contents**  *generated with [DocToc](https://github.com/thlorenz/doctoc)*

- [Embedded Ethics Module 1](#embedded-ethics-module-1)
  - [E1.1. Who is your user?](#e11-who-is-your-user)
  - [E1.2. Disability](#e12-disability)
  - [E1.3. The medical and social models of disability](#e13-the-medical-and-social-models-of-disability)
  - [E1.4. Interventions](#e14-interventions)
  - [E1.5. Material and relational harms](#e15-material-and-relational-harms)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Embedded Ethics Module 1

> Note: this module builds on user stories ([§8.3](08-program-specification.md#83-user-stories))

## E1.1. Who is your user?

To write user stories, you need to understand your user. When you design
software, who do you imagine is going to use it?

Software designers often assume that their user is:

- like themselves, or
- a member of the majority.

And this leads them to design products that **exclude** some people from being
able to use those products.

## E1.2. Disability

One set of users whose needs may differ from the average user are those with
disabilities. The WHO estimates that 15% of users have disabilities.

Examples of disabilities:

- paraplegia (paralysis of lower limbs)
- deafness
- blindness
- mental illness
- speech impairment

What do disabilities have in common with each other?

> Wasserman et al. (2006): a **disability** is a physical or mental
> **impairment** that is associated with a personal or social **limitation** on
> the activities one can perform.

| | Example |
| --- | --- |
| Physical or mental impairment | Paraplegia |
| Personal / social limitation | Not being able to access public spaces |

## E1.3. The medical and social models of disability

Normally we think of the **cause** of an event as another specific event that
occurred before it. Other factors are simply **background conditions**:
required for the event to happen, but not part of the cause.

> The lightning storm causes the forest fire. The availability of oxygen for the
> fire is a background condition.

### The medical model

In some disabilities, the limitations are caused by the physical or mental
impairment. The human world is a background condition.

> impairment **→ causes →** personal / social limitation *(human world:
> background condition)*

### The social model

In some disabilities, the limitations are caused by the human world, whether
through stigma or environmental design. The impairment is a background
condition.

> human world **→ causes →** personal / social limitation *(impairment:
> background condition)*

### Which model?

- **Impairment:** attention deficit disorder
- **Limitation:** difficulty concentrating on schoolwork

Is the limitation caused by the impairment, or by the human world?

For some impairments, and some limitations, the medical model may seem more
appropriate. For some impairments, and some limitations, the social model may
seem more appropriate.

> "Well, I was born with a rare visual condition called achromatopsia, which is
> total color blindness, so I've never seen color, and I don't know what color
> looks like, because I come from a grayscale world… But, since the age of 21,
> instead of seeing color, I can hear color… it's a color sensor that detects
> the color frequency in front of me — (Frequency sounds) — and sends this
> frequency to a chip installed at the back of my head, and I hear the color in
> front of me through the bone, through bone conduction."
>
> — Neil Harbisson, "I Listen to Color" (TED Talk)

Harbisson's account clearly fits the medical model: the limitation comes from
his impairment, and the intervention addresses the impairment itself.

> "Hearing people assume that the Deaf live in a perpetual state of wanting to
> hear, because they can't imagine any other way. But I've never once wished to
> be hearing. I just wanted to be part of a community like me."
>
> — Rebecca Krill, "How Technology has Changed What it's Like to be Deaf" (TED
> Talk)

Krill's account clearly fits the social model: what limits her is not her
impairment but the human world around her.

## E1.4. Interventions

So far we have seen how disabilities (impairment + limitation) reflect the
medical model or the social model. We can also use the medical model and social
model to think about **interventions** that address disability.

- **Medical model:** the intervention reduces the impairment. For example,
  Braille translation software converts text to a document that can be printed
  by a Braille printer.
- **Social model:** the intervention prevents the impairment from causing
  limitations. For example, automatic captioning helps anyone who might
  understand better if they see the words being spoken.

| Medical model | Social model |
| --- | --- |
| "Closer" to the impairment | "Further" from the impairment |
| Can work for one person without working for everyone | Applies to everyone; cannot apply to just one person |
| Need to do something to trigger | Requires little work to trigger |
| Designed with a particular impairment in mind | May be designed with a particular impairment in mind or none in particular in mind |

## E1.5. Material and relational harms

What sort of harms can someone suffer if they are excluded from using software?
It partially depends on what the software does! Consider:

- a rideshare app,
- a messaging app,
- a passport control app, and
- a dating app.

These are **material harms**: loss of happiness, health, freedom,
opportunities, etc. For example, someone excluded from a rideshare app may lose
the freedom to get around on their own; someone excluded from a messaging app
may lose opportunities to stay in touch with friends and family.

But now we will think about a harm caused by exclusion that is much harder to
quantify.

Consider two apps:

| | Works for | Doesn't work for |
| --- | --- | --- |
| **App 1** | Apple users | Android users |
| **App 2** | Young and middle-aged users | Elderly users |

**Why is it worse for App 2 to exclude elderly users than for App 1 to exclude
Android users?**

App 2's design decision harms elderly users **relationally**:

- It expresses that elderly users are less than equal.
- It has the power to demote elderly users' status.

This may be intentional or unintentional. App 1's design decision usually
doesn't do this to Android users.

To sum up:

- **Relational harms** are harms that come from someone expressing that another
  person is less than equal.
- **Material harms** are all other harms (like the harms we identified earlier
  caused by exclusion from various apps: harms to happiness, health, freedom,
  social options, etc.).

### When does exclusion cause relational harm?

In general, when do words or actions cause relational harms? When does a
design decision that excludes someone cause them relational harms?

Philosophers disagree about the answers to these questions! But here are some
factors that may contribute to relational harms:

1. Does the person making the decision have **authority**?
2. What is the **subject matter** of exclusion? Is it closely connected to
   personal dignity or more trivial?
3. **Who is being excluded?** Do they belong to a sensitive group?

On the third factor: some groups of people seem to be more vulnerable to
relational harm through exclusion because of history, etc.

- The relational harm is worst when *all and only* members of a sensitive group
  are excluded.
- It is less relationally harmful if *some but not all* members of a sensitive
  group are excluded.
- It is less relationally harmful if some members of a *non-sensitive* group are
  simultaneously excluded.
