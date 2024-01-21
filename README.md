# Election System

---

## Project Description
The project involves the development of an electronic voting and scrutiny system. The system is designed to be generic and support various voting methods and winner determination.

### Supported Voting Methods
1. **Ordinal Vote:**
   - Voters rank candidates (or groups/parties) on the ballot based on their preferences.

2. **Categorical Vote:**
   - Voters express a preference for a candidate (or group/party).

3. **Categorical Vote with Preferences:**
   - Voters express a preference for a group/party and can indicate one or more preferences among the selected group/party candidates (without split voting).

4. **Referendum:**
   - A question is posed to the electorate with affirmative or negative responses.

### Winner Determination Methods
1. **Majority:**
   - The winner is the candidate who receives the highest number of votes.

2. **Absolute Majority:**
   - The winner is the candidate who obtains an absolute majority of votes (50% + 1).

3. **Referendum Without Quorum:**
   - Vote counting occurs regardless of the participation of the majority of eligible voters.

4. **Referendum With Quorum:**
   - Vote counting occurs only if the majority of eligible voters participate in the consultation.

### User Types
1. **Voter:**
   - Can cast a vote in person at a polling station or remotely.
   - The identification and verification of voting rights can be manual or automated.

2. **Employee/System Manager:**
   - Configures a voting session.
   - Specifies voting methods and winner calculation.
   - Inputs candidate lists.
   - Initiates the scrutiny phase.
   - Views the voting outcome.

### Essential Requirements
1. The cast vote must remain secret and not be traceable to the voter.
2. Each voter can cast only one vote.
3. For each ballot, only one valid vote is allowed, or the exercise of the right to abstain from choosing (blank ballot).
4. Electronic vote counting is activated only after the closure of all voting operations.

### Auditing System
- The auditing system is based on a simplified log system.

---

# Complete Documentation and Diagrams

## Documentation PDF
The comprehensive project documentation, entirely in Italian, including diagrams following the design pattern, is available in the PDF file `gruppo_Capelli_Leoncini.pdf`. The file contains detailed information on the analysis and design of the system.

## Available Diagrams
The PDF includes the following diagrams:
1. Use Case Diagram.
2. Class Diagram.
3. Activity Diagram.
4. Other diagrams specific to the utilized design pattern.

Please refer to the PDF for a complete and detailed view of the project documentation.

---
